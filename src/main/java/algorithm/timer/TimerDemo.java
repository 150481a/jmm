package algorithm.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: jmm
 * @description: 最小堆
 * @Author: xiang
 * @create: 2023/7/24 16:30
 * @Version 1.0
 */
public class TimerDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Task(),1000,2000);
    }
}

class Task extends TimerTask{

    @Override
    public void run() {
        System.out.println("Runing......");
    }
}
