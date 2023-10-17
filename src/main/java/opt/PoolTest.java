package opt;

import java.util.concurrent.*;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/16 16:35
 * @Version 1.0
 */
public class PoolTest {

    public static void main(String[] args) {
       BlockingQueue queue = new ArrayBlockingQueue<>(2);

       ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 20, TimeUnit.MILLISECONDS, queue, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Rejected:"+"满了，请不要塞进来拉");
                System.out.println("Thread-name"+Thread.currentThread().getId());
            }
       });
       while (true){
           executor.execute(new Runnable() {
               @Override
               public void run() {
                   try {Thread.sleep(10000);}catch (Exception e){
                       e.printStackTrace();
                   }
               }
           });
           System.out.println("1");
       }
    }
}
