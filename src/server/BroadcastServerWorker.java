package server;

import java.io.IOException;
import java.util.List;

public class BroadcastServerWorker extends SocketWorkerAdapter {
    @Override
    public void startTalking() throws IOException {

    }

    @Override
    public void listen(String message) throws IOException {

    }

    public byte[] broadcast(byte[] message) {
        return message;
    }

}
