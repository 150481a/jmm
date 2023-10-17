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
public class Token {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore sem = new Semaphore(3);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(sem.availablePermits()<3){
                    sem.release();
                }
            }
        },1000,1000, TimeUnit.MILLISECONDS);

        //等待，等候令牌桶存储
        Thread.sleep(5);

        //模拟洪峰5个请求，前三个迅速响应，后两个排队
        for(int i=0;i<=5;i++){
            sem.acquire();
            System.out.println("洪峰:"+i);
        }

        //模拟日常请求 2s一个

        for(int i=0;i<=3;i++){
            Thread.sleep(1000);
            sem.acquire();
            System.out.println("日常请求:"+i);
            Thread.sleep(1000);
        }

        //再次模拟洪峰5个请求，前三个迅速响应，后两个排队
        for(int i=0;i<=5;i++){
            sem.acquire();
            System.out.println("洪峰:"+i);
        }

        //检查令牌桶的数量
        for(int i=0;i<=5;i++){
            Thread.sleep(2000);
            System.out.println("令牌剩余:"+sem.availablePermits());
        }
    }
}
