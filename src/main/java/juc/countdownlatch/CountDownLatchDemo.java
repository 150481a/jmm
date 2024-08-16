package juc.countdownlatch;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    volatile ArrayList list= new ArrayList();

    public void add(int i){
        list.add(i);
    }

    public int getSize(){
         return  list.size();
    }

    public static void main(String[] args) {
        CountDownLatchDemo c =new CountDownLatchDemo();
        CountDownLatch countDownLatch=new CountDownLatch(1);

        new Thread(()->{
            System.out.println("t2 start ");
            if(c.getSize()!=5){
                try {
                    countDownLatch.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end");
        },"t2").start();

        new Thread(()->{
            System.out.println("t1 start ");
            for (int i = 0; i < 10; i++) {
                c.add(i);
                System.out.println("t1  add "+i);
                if (c.getSize()==5){
                    System.out.println("countdown is open ");
                    countDownLatch.countDown();
                }
            }
            System.out.println("t1 end");
        },"t1").start();
    }


}
