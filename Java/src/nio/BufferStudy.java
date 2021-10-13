package nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferStudy {
    public static void studyBuffer() throws Exception {
        //1
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //2
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes);

        printfStatus(byteBuffer, "버퍼1 생성");
        printfStatus(byteBuffer2, "버퍼2 생성");

        //버퍼에 값 넣기
        byteBuffer.put((byte) 55);
        byteBuffer.put((byte) -12);

        printfStatus(byteBuffer, "버퍼1 2바이트 추가");

        byteBuffer.put(new byte[]{1, 2, 3, 4});

        byte[] byte1=new byte[3];
        printfStatus(byteBuffer, "버퍼1 바이트 배열 추가");

        byte value = byteBuffer2.get();
        System.out.println("get : " + value);
        printfStatus(byteBuffer2, "버퍼2 get");

        byteBuffer.flip();
        printfStatus(byteBuffer, "flip");

        value = byteBuffer.get();
        System.out.println("get : " + value);
        printfStatus(byteBuffer, "버퍼1 get");

        printfStatus(byteBuffer,"버퍼1 rewind before");
        byteBuffer.rewind();
        printfStatus(byteBuffer,"버퍼1 rewind");

//        printfStatus(byteBuffer,"버퍼1 clear before");
//        byteBuffer.clear();
//        printfStatus(byteBuffer,"버퍼1 clear");

        byteBuffer.get(byte1);
        printfStatus(byteBuffer,"버퍼1");
        byteBuffer.mark();

        byteBuffer.get();
        printfStatus(byteBuffer,"버퍼1");
//        byteBuffer.reset();
//        printfStatus(byteBuffer,"버퍼1 mark & reset");

        byteBuffer.compact();
        printfStatus(byteBuffer,"버퍼1 compact");
    }

    public static void printfStatus(Buffer buffer, String note) {
        System.out.println("-------------------------------");
        System.out.println(note);
        System.out.print("limit : "+buffer.limit() + " ");
        System.out.print("capacity : "+buffer.capacity() + " ");
        System.out.println("position : "+buffer.position());
    }

    public static void main(String[] args) {
        try {
            studyBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
