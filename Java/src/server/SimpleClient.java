package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class SimpleClient {
    private Socket socket;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 8000;

    private InputStream is;
    private OutputStream os;
    private Scanner scanner;

    public void connect() throws IOException {
        socket = new Socket(SERVER_ADDRESS, PORT);
    }

    public void disconnect() throws IOException {
        if (is != null) {
            is.close();
        }
        if (os != null) {
            os.close();
        }
        if (socket != null) {
            socket.close();
        }
    }

    public void prepareTalking() throws Exception {
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public void processUserInput() throws Exception {
        scanner = new Scanner(System.in);
        String message = null;
        while (true) {
            message = scanner.nextLine();
            if ("quit".equals(message)) break;
            sendMessage(message);
            String returnMessage = receiveMessage();
            System.out.println("echo : " + returnMessage);
        }
        scanner.close();
        disconnect();
    }

    public String receiveMessage() throws Exception {
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        return new String(buffer, 0, length);
    }

    public void sendMessage(String message) throws Exception {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        os.write(bytes);
    }

    public void startTalking() throws IOException {
        String message = "안녕하세요";
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        os.write(bytes);

    }

    public static void main(String[] args) {
        try {
            SimpleClient client = new SimpleClient();
            client.connect();
            client.prepareTalking();
            client.processUserInput();
//            client.startTalking();
//            client.disconnect();
        } catch (Exception e) {

        }

    }
}
