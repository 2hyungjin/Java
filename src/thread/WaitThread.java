package thread;

import java.util.Scanner;

public class WaitThread extends Thread {
    private Object object;

    public WaitThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        try {
            System.out.println(getId() + "깨워줘");
            synchronized (object) {
                object.wait();
            }
            System.out.println(getId() + "잘 잤다");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Object obj = new Object();
        WaitThread t1 = new WaitThread(obj);
        WaitThread t2 = new WaitThread(obj);
        t1.start();
        t2.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        synchronized (obj) {
            obj.notifyAll();
        }
        scanner.close();
    }
}
