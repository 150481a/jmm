package juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/9 14:21
 * @Version 1.0
 */
public class NesteLock {

    public synchronized void f1(){
        System.out.println("f1");
    }
    public synchronized void f2(){
        f1();
        System.out.println("f2");
    }

    public static void main(String[] args) {
        NesteLock nesteLock = new NesteLock();
        nesteLock.f2();
        ReentrantLock reentrantLock = new ReentrantLock(true);

    }


}
