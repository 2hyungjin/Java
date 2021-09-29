package assessment.client;

import java.net.Socket;
import java.nio.channels.SocketChannel;

public class User {
    public String id;
    public String name;
    public Socket socket;
    public Boolean isAdmin=false;


    public User(Socket socket) {
        this.socket = this.socket;
    }

    public User(String id, String name, Socket socket) {
        this.id = id;
        this.name = name;
        this.socket = socket;
    }

    public void setInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", channel=" + socket +
                '}';
    }
}
