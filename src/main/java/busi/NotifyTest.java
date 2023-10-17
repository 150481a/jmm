package busi;

/**
 * @program: jmm
 * @description: wait()/notifyAll
 * @Author: xiang
 * @create: 2023/6/19 10:08
 * @Version 1.0
 */
public class NotifyTest {

    public static void main(String[] args) throws InterruptedException {
       byte[] lock = new byte[0];

       Thread t1=new Thread(()->{
           synchronized (lock) {
               try {
                   lock.wait();
                   System.out.println("t1");
               }catch (Exception e) {
                   e.printStackTrace();
               }

           }


       });
        Thread t2=new Thread(()->{
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("t2");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3=new Thread(()->{
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("t3");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t4=new Thread(()->{
            synchronized (lock){
                try {
                    Thread.sleep(1000);
                    System.out.println("t4");
                    lock.notifyAll();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        t1.setPriority(2);
        t2.setPriority(1);
        t3.setPriority(5);
        t4.setPriority(10);
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(1000); //不加这条可能会引起线程等待 即启动顺序为  t1 t4 t2 t3,导致t2 t3 会几率存在一直等待中
        t4.start();

    }
}
