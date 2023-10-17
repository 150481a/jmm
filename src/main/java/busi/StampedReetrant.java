package busi;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: jmm
 * @description: StampedLock  不可重入锁
 * @Author: xiang
 * @create: 2023/6/19 15:42
 * @Version 1.0
 */
public class StampedReetrant {
    public static void main(String[] args) {
        StampedLock lock=new StampedLock();
        long l = lock.writeLock();
        System.out.println("l:"+l);
        long l2 = lock.writeLock();
        System.out.println("l2:"+l2);
        lock.unlock(l2);
        lock.unlock(l);

    }
}
