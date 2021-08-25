package thread;

public class MainClass {
    public static void main(String[] args) {
        Thread t1 = new MyThread();
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        System.out.println(t1.getId());


        MyRunnable r1 = new MyRunnable();
        Thread t2 = new Thread(r1);
        t2.setPriority(Thread.MIN_PRIORITY);
        t2.start();
        System.out.println(t2.getId());
        System.out.println(t2.isAlive());
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {

        }
        for (int i = 0; i < 100; i++) {
            System.out.println("Main " + i);
        }
        System.out.println(t2.isAlive());

    }
}
