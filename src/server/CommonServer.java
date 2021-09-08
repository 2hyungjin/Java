package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommonServer {

    private ServerSocket serverSocket;
    private List<Agent> agentList;

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            agentList = new ArrayList<>();
            while (true) {
                Socket socket = serverSocket.accept();

                Agent agent = new Agent(socket);
                new Thread(agent).start();
                agentList.add(agent);
            }

        } catch (Exception e) {
        }
    }

    private class Agent implements Runnable {

        private Socket socket;

        public Agent(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BroadcastServerWorker socketWorker = new BroadcastServerWorker();
                socketWorker.setSocket(socket);
                socketWorker.prepareTalking();
                socketWorker.startTalking();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        try {
            CommonServer server = new CommonServer();
            server.startServer(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}