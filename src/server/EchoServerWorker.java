package server;

import java.io.IOException;

import java.io.IOException;

public class EchoServerWorker extends SocketWorkerAdapter {

    @Override
    public void startTalking() throws IOException {

    }

    @Override
    public void listen(String message) throws IOException {
        this.os.write(message.getBytes());
    }

}
