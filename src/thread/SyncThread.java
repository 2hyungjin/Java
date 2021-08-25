package thread;

public class SyncThread extends Thread {
    private int value;
    private Calculator calculator;

    public SyncThread(int value, Calculator calculator) {
        this.value = value;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            calculator.add(value);
        }
        System.out.println(getId() + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        SyncThread t1 = new SyncThread(1, calculator);
        t1.start();
        SyncThread t2 = new SyncThread(-1, calculator);
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(calculator.getSum());
        System.out.println("종료" + System.currentTimeMillis());

    }
}
