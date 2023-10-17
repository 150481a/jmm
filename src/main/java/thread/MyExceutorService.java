package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/7 17:44
 * @Version 1.0
 */
public class MyExceutorService extends ThreadPoolExecutor {
    public MyExceutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void terminated() {
        super.terminated();
        System.out.println("terminated");
    }

    public static void main(String[] args) {
        MyExceutorService myExceutorService = new MyExceutorService(1, 2, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(5));
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile");
            }
        });
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile1");
            }
        });
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile2");
            }
        });
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile2");
            }
        });
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile3");
            }
        });
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile4");
            }
        });
        myExceutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("kuaile5");
            }
        });


    }
}
