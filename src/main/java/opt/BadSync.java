package opt;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/13 17:48
 * @Version 1.0
 */
public class BadSync implements  Runnable {
     volatile int i=0;
    long startTime = System.currentTimeMillis();
     volatile long  totalTime = 0;
    public synchronized void inc(){i++;}
//    @Override
//    public synchronized void run1() {
//
//        try {
//            Thread.sleep(100);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        inc();
//        totalTime+=(System.currentTimeMillis()-startTime);
//
//    }

    @Override
    public  void run() {

        try {
            Thread.sleep(100);
        }catch(Exception e){
            e.printStackTrace();
        }
        inc();
        totalTime+=(System.currentTimeMillis()-startTime);
    }

    public static void main(String[] args) throws InterruptedException {
        BadSync badSync = new BadSync();
        for (int i=0; i<5;i++){
            new Thread(badSync).start();
        }
        Thread.sleep(3000);

        System.out.println("最终计数 i:"+badSync.i);
        System.out.println("最终时间 totalTime:"+badSync.totalTime);

    }
}
