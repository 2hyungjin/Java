package assessment.client;

import assessment.util.Listener;
import assessment.util.ListenerThread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client implements Listener {
    private ListenerThread listenerThread;
    private Socket socket;
    User user;


    public Client(Socket socket) {
        this.socket = socket;
        user = new User(socket);

        listenerThread = new ListenerThread(socket, this);
        System.out.println("make lt /: " + listenerThread.toString() + listenerThread.hashCode());
        receiveMessage();
    }

    public void sendMessage(String head, String payload) {
        byte[] message = String.format("%s%04d%s", head, payload.length(), payload).getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(message));
        try {
            OutputStream os = socket.getOutputStream();
            os.write(message);
            os.flush();
        } catch (IOException e) {
            closeSocket(socket);
        }
    }

    private void receiveMessage() {
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    private void analyzeMessage(String head, String payload, Socket socket) {
        switch (head) {
            case "DR":
                executeDR();
                break;
            case "UR":
                executeUR(payload);
                break;
            case "JR":
                executeJR(payload);
                break;
            case "GR":
                executeGR(payload);
                break;
            case "SM":
                executeSM(payload);
                break;
            case "SR":
                executeSR(payload);
                break;
            case "DC":
                executeDC(payload);
                break;
            case "WR":
                executeWR();
                break;
            case "WA":
                executeWA(payload);
                break;
        }
    }

    @Override
    public void onListened(String head, String payload, Socket socket) {
        analyzeMessage(head, payload, socket);
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(-1);
    }

    private void executeUR(String payload) {
        System.out.println(payload.replace("[", "").replace("]", ""));
    }

    private void executeJR(String payload) {
        System.out.println(payload);
    }

    private void executeGR(String payload) {
        System.out.println(payload);
    }

    private void executeSM(String payload) {
        System.out.println(payload);
    }

    private void executeSR(String payload) {
        System.out.println(payload);
    }

    private void executeWR() {
        closeSocket(socket);
    }

    private void executeDR() {
        closeSocket(socket);
    }

    private void executeDC(String payload) {
        System.out.println(payload);
    }

    private void executeWA(String payload) {
        System.out.println(payload);
    }


    @Override
    public void onListenFailed(Socket socket) {
        closeSocket(socket);
    }

}
