package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AddClient {
    private final String SERVER_ADDRESS = "127.0.1";
    private final int PORT = 8000;

    Socket socket;
    Scanner scanner;
    InputStream is;
    OutputStream os;

    public void serverConnect() throws IOException {
        socket = new Socket(SERVER_ADDRESS, PORT);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        scanner = new Scanner(System.in);
    }

    public void sendValue() throws IOException {
        int value1 = scanner.nextInt();
        int value2 = scanner.nextInt();
        byte[] bytes = new byte[8];
        String message = String.format("%d,%d", value1, value2);
        os.write(message.getBytes(StandardCharsets.UTF_8));
        System.out.println(is.read());
    }

    public static void main(String[] args) {
        AddClient adc = new AddClient();
        try {
            adc.serverConnect();
            while (true) {
                adc.sendValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
