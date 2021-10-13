package thread;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private int sum = 0;
    private List<String> list = new ArrayList<>();

    public void add(int value) {
        synchronized (this){
            sum += value;
        }
    }

    public int getSum() {
        return sum;
    }
}
