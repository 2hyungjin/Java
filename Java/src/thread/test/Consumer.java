package thread.test;

public class Consumer extends Thread {
    Product product;

    Consumer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            if (!(product.getCount() <= 0)) {
                consume();
            } else {
                synchronized (product) {
                    try {
                        System.out.println("wait..");
                        product.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void consume() {
        System.out.println("consume");
        product.count--;
    }
}
