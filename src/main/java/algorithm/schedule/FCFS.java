package algorithm.schedule;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/24 13:59
 * @Version 1.0
 */
public class FCFS {

    public static void main(String[] args) throws InterruptedException {

        //阻塞队列，FCFS基础
        final LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<>();

        //服务线程，任务由该线程执行
        new Thread(()->{

            while (true){
                try {
                    queue.take().execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();

        //服务线程，任务由该线程执行
        new Thread(()->{

            while (true){
                try {
                    queue.take().execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();

        //向队列中放入任务
        for (int i = 0; i < 100; i++) {
            System.out.println("add task:"+i);
            queue.put(new Task("task"+i,new Random().nextInt(1000)));
        }

    }
}
