package assessment.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientExecutor {
    public static void main(String[] args) {
        Client client;
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(1200));
            client = new Client(socket);
            System.out.println("ID 이름을 붙여서 입력하세요");
            String message = scanner.nextLine();
            client.sendMessage("ID", message);
            while (true) {
                message = scanner.nextLine();
                client.sendMessage(message.substring(0, 2), message.substring(2));
                if (message.equals("quit")){
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("연결 종료");
            e.printStackTrace();
        }
    }
}
