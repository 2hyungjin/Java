package assessment.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ListenerThread extends Thread {
    private Socket socket;
    public InputStream is;

    private Listener listener;


    public ListenerThread(Socket socket, Listener listener) {
        this.socket = socket;
        try {
            is = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listener = listener;
    }


    @Override
    public void run() {
        byte[] bytes = new byte[1024];
        int length;

        try {
            while (true) {
                length = is.read(bytes);
                if (length > 0) {
                    listen(bytes);
                }
            }
        } catch (Exception e) {
            listener.onListenFailed(socket);
        }
    }

    private void listen(byte[] bytes) {
        String head = new String(bytes, 0, 2);
        int length = Integer.parseInt(new String(bytes, 2, 4));
        String payload = new String(bytes, 6, length);
        System.out.println("listener " + payload);
        listener.onListened(head, payload, socket);
    }

}
