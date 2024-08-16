package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/8 15:43
 * @Version 1.0
 */
public class PrivateLock {
    Lock lock=new ReentrantLock();//独享锁
    ReentrantReadWriteLock locak2=new ReentrantReadWriteLock();//共享锁  读写锁

    long start=System.currentTimeMillis();

    void read(int i){
//        locak2.readLock().lock(); 读锁  共享锁
        locak2.writeLock().lock(); //写锁   独享所
        try{
            Thread.sleep(100);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
//            locak2.readLock().unlock();
            locak2.writeLock().unlock();
        }
        System.out.println("read"+i+" time="+(System.currentTimeMillis()-start));
    }

    public static void main(String[] args) {
        final PrivateLock privateLock=new PrivateLock();
        for (int i = 0; i <10 ; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    privateLock.read(finalI);
                }
            }).start();
        }
    }

}
