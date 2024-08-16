package juc.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 10:27
 * @Version 1.0
 */
public class AQSlock  extends AbstractQueuedSynchronizer {

    public AQSlock(int count){
        setState(count);
    }

    @Override
    protected int tryAcquireShared(int arg) {
       for (;;){
           int state = getState();
           int newCount=state-arg;
           if(newCount<0||compareAndSetState(state,newCount))
               return newCount;
       }
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        for (;;){
            int state = getState();
            int newstate = state + arg;
            if(compareAndSetState(state,newstate))return true;
        }
    }


    public static void main(String[] args) {
       final AQSlock aqSlock = new AQSlock(3);
        for (int i = 0; i < 30; i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    aqSlock.acquireShared(1);
                    try {
                        Thread.sleep(2000);
                        System.out.println("ok");
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally {
                        aqSlock.releaseShared(1);
                    }


                }
            }).start();
        }
    }
}
