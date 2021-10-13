package assessment.util;

import java.net.Socket;

public interface Listener {
    void onListened(String head, String payload, Socket socket);

    void onListenFailed(Socket socket);
}
