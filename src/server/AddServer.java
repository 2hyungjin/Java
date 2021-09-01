package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AddServer {
    private ServerSocket serverSocket;

    void connectServer() throws IOException {
        serverSocket = new ServerSocket(8000);
    }

    void enterClient() throws IOException {
        Socket client = serverSocket.accept();
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        int value1, value2;
        byte[] bytes = new byte[1024];
        while (true) {
            int length = is.read(bytes);
            String message = new String(bytes, 0, length);
            String[] splitMessage= message.split(",");
            value1 = Integer.parseInt(splitMessage[0]);
            value2 = Integer.parseInt(splitMessage[1]);
            os.write(value1+value2);
        }
    }

    public static void main(String[] args) {
        AddServer ads = new AddServer();
        try {
            ads.connectServer();
            ads.enterClient();
        } catch (Exception e) {

        }
    }
}
