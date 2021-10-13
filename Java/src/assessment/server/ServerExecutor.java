package assessment.server;

import java.io.IOException;

public class ServerExecutor {
    public static void main(String[] args) {
        Server server= new Server();
        try {
            server.serverInit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
