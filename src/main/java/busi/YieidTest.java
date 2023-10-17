package busi;

/**
 * @program: jmm
 * @description: yield  不释放锁  锁的状态为变为就绪状态  让出cpu  二则区别（sleep  yieid），Thread.yield()依赖于CPU的时间片划分，Thread.sleep()会抛出中断异常，且能被中断，而Thread.yield()不可以；
 * @Author: xiang
 * @create: 2023/6/19 10:44
 * @Version 1.0
 */
public class YieidTest {

    public static void main(String[] args) {
        byte[] lock = new byte[0];
        Thread t1=new Thread(()->{
            synchronized (lock){
                System.out.println("start");
                Thread.yield();
                try {}catch (Exception e){e.printStackTrace();}
                System.out.println("end");
            }
        });

        Thread t2=new Thread(()->{
            synchronized (lock){
                System.out.println("t2");
            }

        });
        Thread t3=new Thread(()->{
                System.out.println("t3");

        });

        t1.start();
        t2.start();
        t3.start();
    }
}
