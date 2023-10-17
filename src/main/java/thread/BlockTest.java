package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: jmm
 * @description: 线程的状态更改  两个线程抢锁 ，查看锁的状态变更  之所以用byte  是因为 byte 加锁消耗资源最少
 * @Author: xiang
 * @create: 2023/6/7 16:24
 * @Version 1.0
 */
public class BlockTest {

    public static void main(String[] args) throws InterruptedException {
        byte[] lock = new byte[0];
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
//                    Thread.sleep(1000);
                    LockSupport.park();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2");
            }
        });
        t1.setPriority(9);//设置线程优先值
        t2.setPriority(1);
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(t1.getName()+":"+t1.getState());
        System.out.println(t2.getName()+":"+t2.getState());
        Thread.sleep(1000);
        System.out.println(t2.getState());
        LockSupport.unpark(t1);
        System.out.println(t1.getName()+":"+t1.getState());



    }
}
