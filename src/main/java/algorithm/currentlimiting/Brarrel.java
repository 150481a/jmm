package algorithm.currentlimiting;

import java.util.concurrent.*;

/**
 * @program: jmm
 * @description: 计数器
 * @Author: xiang
 * @create: 2023/7/19 11:13
 * @Version 1.0
 */
public class Brarrel {

    public static void main(String[] args) {
        final LinkedBlockingQueue<Integer> que = new LinkedBlockingQueue(3);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Integer poll = que.poll();
                System.out.println("处理："+poll);
            }
        },2000,2000, TimeUnit.MILLISECONDS);

        int i=0;
        while (true){
            i++;
            try {
                System.out.println("put:"+i);
                //如果是put,会一直等待桶中有空闲位置，不会丢弃
                //que.put(i);
                //等待1 秒，如果进不了桶，就会溢出丢弃
                que.offer(i,1000,TimeUnit.MILLISECONDS);
            }catch (Exception e){
                e.printStackTrace();
            }
            //如果准响应  打印一个ok
            System.out.println("ok");


        }



    }
}
