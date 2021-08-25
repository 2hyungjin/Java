package thread;

public class WaitThread extends Thread{
    private Object object;
    public WaitThread(Object object){
        this.object=object;
    }

    @Override
    public void run() {
        try{
            synchronized (object){
                object.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
