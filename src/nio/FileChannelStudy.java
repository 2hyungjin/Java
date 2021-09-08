package nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelStudy {
    public static void studyWrite() throws Exception {
        Path path = Paths.get("/Users/jinstonlee/Documents/study/nio/abc.txt");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        String data = "대구소프트웨어고";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        channel.write(byteBuffer);

        channel.close();
    }

    public static void studyRead() throws Exception {
        Path path = Paths.get("/Users/jinstonlee/Documents/study/nio/abc.txt");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        byte[] bytes = new byte[100];
        String data = "";

        while (true) {
            int count = channel.read(buffer);
            buffer.flip();
            if (count < 0) break;
            buffer.get(bytes);
            data += new String(bytes, 0, count, "UTF-8");
            buffer.clear();
        }
    }

    public static void main(String[] args) {
        try {
            studyWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
