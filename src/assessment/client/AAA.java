package assessment.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class AAA {

    public static final void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 1200);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Thread.sleep(100l);

            os.write("ID0006abcd11".getBytes(StandardCharsets.UTF_8));

            byte[] bytes = new byte[1024] ;
            int length = is.read(bytes);

            System.out.println("receive : " + new String(bytes, 0, length));

            Thread.sleep(1000l);

            is.close();
            os.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
