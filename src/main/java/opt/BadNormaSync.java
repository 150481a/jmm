package opt;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/15 11:26
 * @Version 1.0
 */
public class BadNormaSync implements Runnable{

    long  start=System.currentTimeMillis();
    volatile int i=0;

    @Override
    public synchronized void run() {
       int j=i;
       try {Thread.sleep(100);}catch(Exception e){e.printStackTrace();}
       i=j+1;
       System.out.println(Thread.currentThread().getId()+"ok,time="+(System.currentTimeMillis()-start));
    }

    public static void main(String[] args) throws InterruptedException {
        BadNormaSync badNormaSync=new BadNormaSync();
        new Thread(badNormaSync).start();
        new Thread(badNormaSync).start();
        Thread.sleep(1000);
        System.out.println("value="+badNormaSync.i);

    }
}
