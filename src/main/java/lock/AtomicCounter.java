package lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 11:07
 * @Version 1.0
 */
public class AtomicCounter {


//    private static int i=0;
    private static AtomicInteger i=new AtomicInteger(0);
    public  int get(){return i.get();}
    public void inc(){
//        int j=get();
        try {
            Thread.sleep(100);
            i.incrementAndGet();

//            i=andIncrement;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicCounter atomicCounter = new AtomicCounter();
        for(int i=0;i<30;i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    atomicCounter.inc();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("i:"+atomicCounter.get());
    }

}
