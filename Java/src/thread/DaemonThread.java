package thread;

public class DaemonThread extends Thread{
    @Override
    public void run() {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(getId()+" : " + i);
//        }
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DaemonThread t1= new DaemonThread();
        DaemonThread t2= new DaemonThread();

        t1.setDaemon(true);
        t2.setDaemon(false);
        t1.start();
        t2.start();

    }
}