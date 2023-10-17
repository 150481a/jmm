package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: jmm
 * @description: 线程池
 * @Author: xiang
 * @create: 2023/6/7 17:37
 * @Version 1.0
 */
public class PoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(500);
                    System.out.println("T1");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        executorService.shutdownNow();

        executorService.execute(new Runnable() {
            @Override
            public void run() {

              System.out.println("T2");

            }
        });


    }


    interface  teet{
        int k=10;
        public void test();
    }
    abstract class tes{
        int k=10;
        public void test(){}
    }

    class st extends tes implements  teet{

        transient int k=10;



    }

}
