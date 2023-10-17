package busi;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/19 15:34
 * @Version 1.0
 */
public class StampedThread {
    StampedLock lock=  new StampedLock();

    void write(){
        long stamp = lock.writeLock();
        try {
            Thread.sleep(1000);
        }catch(Exception e){e.printStackTrace();}
        lock.unlock(stamp);
    }

    void read(){
        //乐观读
        long stamp = lock.tryOptimisticRead();
        //判断是否有写在存在，没有占用的话，得到执行，打印read
        if(lock.validate(stamp)){
            System.out.println("read");
        }
    }

    public static void main(String[] args) {
        StampedThread stampedThread=new StampedThread();
        Thread t1 = new Thread(() -> {
            while (true){
                stampedThread.write();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true){
                stampedThread.read();
            }
        });

        t1.setPriority(9);
        t2.setPriority(1);
        t1.start();
        t2.start();
    }
}
