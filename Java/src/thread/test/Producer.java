package thread.test;

import java.util.Random;

public class Producer extends Thread {
    Product product;

    Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true){
            produce();
            synchronized (product) {
                product.notify();
            }
        }
    }

    private void produce() {
        int cycle = new Random().nextInt(5) * 1000;
        try {
            sleep(cycle + product.cycle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (product) {
            product.count++;
        }
        System.out.println("produce");

    }

}
