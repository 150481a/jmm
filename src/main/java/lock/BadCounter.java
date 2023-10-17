package lock;

import java.rmi.server.ExportException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/8 15:10
 * @Version 1.0
 */
public class BadCounter {

    private static   int i=0;
    Lock lock=new ReentrantLock();

    public  int get() {
        return i;
    }

    public  void inc(){
//        lock.lock();
        synchronized(BadCounter.class){
            int j=get();
            try{

                Thread.sleep(100);

                j++;


            }catch (Exception e){
                e.printStackTrace();
            }
            i=j;
        }

//        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 11; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new BadCounter().inc();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("i:"+i);
    }
}
