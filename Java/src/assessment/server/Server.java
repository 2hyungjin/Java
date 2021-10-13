package assessment.server;

import assessment.client.User;
import assessment.util.Listener;
import assessment.util.ListenerThread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//10.80.162.84
public class Server implements Listener {
    private static final int PORT = 1200;
    private ServerSocket serverSocket;
    private ListenerThread listenerThread;

    private List<User> userList = null;

    void serverInit() throws IOException {
        //서버 열기
        serverSocket = new ServerSocket(PORT);
        Socket socket;

        //클라이언트 리스트 생성
        userList = new ArrayList<>();

        //클라이언트 대기
        while (true) {
            socket = serverSocket.accept();
            connectClient(socket);
        }
    }

    private void connectClient(Socket socket) throws IOException {
        System.out.println("client arrives");

        //리스너 쓰레드
        listenerThread = new ListenerThread(socket, this);
        listenerThread.start();
    }

    //서버 닫기
    void closeServer() throws IOException {
        serverSocket.close();
    }

    private void analyzeMessage(String head, String payload, Socket socket) {
        switch (head) {
            case "ID":
                System.out.println("ID called");
                executeID(payload, socket);
                break;
            case "GM":
                System.out.println("GM called");
                executeGM(payload, socket);
                break;
            case "SM":
                System.out.println("SM called");
                executeSM(payload, socket);
                break;
            case "WD":
                System.out.println("WD called");
                executeWD(payload, socket);
                break;
        }
    }

    private void closeSocket(Socket socket) {
        System.out.println("socket is closed");
        try {
            // 소켓 끊기
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(User target, String head, String payload) {
        sendMessage(target.socket, head, payload);
    }

    private void sendMessage(Socket socket, String head, String payload) {
        byte[] message = String.format("%s%04d%s", head, payload.length(), payload).getBytes(StandardCharsets.UTF_8);
        try {
            OutputStream os = socket.getOutputStream();
            os.write(message);
            os.flush();
        } catch (Exception e) {
            onListenFailed(socket);
            e.printStackTrace();
        }
    }

    @Override
    public void onListened(String head, String payload, Socket socket) {
        analyzeMessage(head, payload, socket);
    }

    private User findTarget(String id) {
        for (User user : userList) {
            if (user.id != null) {
                if (user.id.equals(id)) {
                    return user;
                }
            }
        }
        return null;
    }

    private User findTarget(Socket socket) {
        for (User user : userList) {
            if (user.socket == socket) {
                return user;
            }
        }
        return null;
    }


    private void executeID(String payload, Socket socket) {
        String id = payload.substring(0, 4);
        String name = payload.substring(4);

        User user = new User(id, name, socket);
        //리스트가 비어있으면 어드민
        if (userList.isEmpty()) {
            user.isAdmin = true;
        } else {
            //리스트에 해당 id 유저가 있으면 DR 반환
            if (userList.contains(findTarget(id))) {
                sendMessage(socket, "DR", "0000");
                closeSocket(socket);
                return;
            }
        }
        //새 유저 생성 후 리스트에 추가
        userList.add(user);

        //UR 반환
        List<String> userListFordata = new ArrayList<>();
        for (
                User u : userList) {
            userListFordata.add(u.id + u.name);
        }

        sendMessage(socket, "UR", userListFordata.toString().
                replaceAll(" ", "")
                .replace("[", "")
                .replace("]", ""));

        //JR 반환
        for (User u : userList) {
            if (u != user) {
                sendMessage(u.socket, "JR", user.id + " " + user.name);
            }
        }
    }

    private void executeGM(String message, Socket socket) {
        //GR 보내기
        User sender = findTarget(socket);
        for (User u : userList) {
            sendMessage(u, "GR", sender.id + sender.name + " " + message);
        }
    }

    private void executeSM(String message, Socket socket) {
        //SR 보내기
        String receiver = message.substring(0, 4);
        String sender = findTarget(socket).id;
        User target = findTarget(receiver);
        String payload = sender + " -> " + message.substring(4);

        if (target != null) {
            sendMessage(target.socket, "GR", payload);
        }
    }

    private void executeWD(String message, Socket socket) {
        User target = findTarget(message);
        if (target != null) {
            if (findTarget(socket).isAdmin) {
                for (User u : userList) {
                    if (message.equals(u.id)) {
                        //WR 반환
                        sendMessage(u.socket, "WR", "0000");
                        closeSocket(u.socket);
                    } else {
                        //WA 반환
                        sendMessage(u.socket, "WA", target.id);
                    }
                }
            }
            userList.remove(target);
        }

    }

    @Override
    public void onListenFailed(Socket socket) {
        System.out.println("DC called");
        User target = findTarget(socket);
        for (User u : userList) {
            if (u == target) {
                closeSocket(socket);
            } else {
                sendMessage(u.socket, "DC", target.id);
            }
        }
        userList.remove(target);
    }
}

