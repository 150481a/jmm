package busi;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: jmm
 * @description: 饥饿线程   产生原因 高优先线程吞噬所有的低优先线程的CPU时间。 锁始终被别的线程抢占
 *  解决方案： 1、保证资源充足   2、避免持有锁的线程长时间执行，设置一定的退出机制 3、在高风险地方使用公平锁
 * @Author: xiang
 * @create: 2023/6/19 15:24
 * @Version 1.0
 */
public class HungryThread {

    ReentrantReadWriteLock readWriteLock=  new ReentrantReadWriteLock();

    void write(){
        readWriteLock.writeLock().lock();
        try {
            Thread.sleep(1000);
        }catch(Exception e){e.printStackTrace();}
        readWriteLock.writeLock().unlock();
    }
    void read(){
        readWriteLock.readLock().lock();
        System.out.println("read");
        readWriteLock.readLock  ().unlock();
    }

    public static void main(String[] args) {
        HungryThread hungryThread = new HungryThread();
        Thread t1 = new Thread(() -> {
            while (true){
                hungryThread.write();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true){
                hungryThread.read();
            }
        });

        t1.setPriority(1);
        t2.setPriority(1);
        t1.start();
        t2.start();

    }
}
