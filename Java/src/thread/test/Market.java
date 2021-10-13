package thread.test;

public class Market {
    public static void main(String[] args) {
        Product product = new Product();
        product.cycle=0;
        product.count=3;
        Producer producer = new Producer(product);
        Consumer consumer = new Consumer(product);
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
