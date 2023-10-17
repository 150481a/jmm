package busi;

/**
 * @program: jmm
 * @description: 死锁演示
 * @Author: xiang
 * @create: 2023/6/19 14:40
 * @Version 1.0
 */
public class DeadLock {
    byte[] lock=new byte[0];
    byte[] lock1=new byte[0];

    public void f1() throws InterruptedException {
        synchronized (lock){
            Thread.sleep(100);
            System.out.println("f1.lock");
            synchronized (lock1){
                System.out.println("f1.lock1");
            }
        }
    }

    public void f2() throws InterruptedException {
        synchronized (lock1){
            Thread.sleep(100);
            System.out.println("f2.lock1");
            synchronized (lock){
                System.out.println("f2.lock");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(()->{
            try {
                deadLock.f1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()->{
            try {
                deadLock.f2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
