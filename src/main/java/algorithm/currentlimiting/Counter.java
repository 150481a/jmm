package algorithm.currentlimiting;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: jmm
 * @description: 计数器
 * @Author: xiang
 * @create: 2023/7/19 11:13
 * @Version 1.0
 */
public class Counter {

    public static void main(String[] args) {
        final Semaphore sem = new Semaphore(3);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                sem.release(3);
            }
        },3000,3000, TimeUnit.MILLISECONDS);
        while (true){
            try {
                sem.acquire();
            }catch (Exception e){
                e.printStackTrace();
            }
            //如果准响应  打印一个ok
            System.out.println("ok");


        }



    }
}
