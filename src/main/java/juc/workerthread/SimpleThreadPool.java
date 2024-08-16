package juc.workerthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i <50; i++) {
            WorkerThreadDemo workerThreadDemo = new WorkerThreadDemo(""+i);
            executorService.execute(workerThreadDemo);
        }
        //这将使执行器不接受新线程并完成队列中的所有现有线程
        executorService.shutdown();

        //等待所有线程完成，也可以使用“executor.awaitTermination();”等待
        while (!executorService.isTerminated()){}
        System.out.println("Finished all threads");
    }
}
