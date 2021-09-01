package server;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    private ServerSocket serverSocket;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 8000;

    public void startServer() throws Exception {
        serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress().toString());
            startTalking(socket);
        }
    }

    public void startTalking(Socket socket) {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            byte[] bytes = new byte[4096];
            int length = is.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SimpleServer server = new SimpleServer();
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
