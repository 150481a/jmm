package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/8 14:56
 * @Version 1.0
 */
public class BlockTest2 {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(50000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("ok");
    }
}
