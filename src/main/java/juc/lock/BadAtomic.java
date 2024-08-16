package juc.lock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jmm
 * @description: Atomic 原子性破坏案例
 * @Author: xiang
 * @create: 2023/6/12 11:07
 * @Version 1.0
 */
public class BadAtomic {


//    private static int i=0;
    private static AtomicInteger i=new AtomicInteger(0);

    static int k=0;
    public  int get(){return i.get();}
    public void inc(){
        int j = i.incrementAndGet();
        try {
            Thread.sleep(new Random().nextInt(100));
            k=j;
//            i=andIncrement;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BadAtomic atomicCounter = new BadAtomic();
        for(int i=0;i<30;i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    atomicCounter.inc();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("k:"+BadAtomic.k);
        System.out.println("i:"+i.get());
    }

}
