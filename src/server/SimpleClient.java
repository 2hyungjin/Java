package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class SimpleClient {
    private Socket socket;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 8000;

    private InputStream is;
    private OutputStream os;

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

    public void startTalking() throws IOException {
        String message = "HELLO";
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        os.write(bytes);
    }

    public static void main(String[] args) {
        try {
            SimpleClient client = new SimpleClient();
            client.connect();
            client.prepareTalking();
            client.startTalking();
            client.disconnect();
        } catch (Exception e) {

        }

    }
}
