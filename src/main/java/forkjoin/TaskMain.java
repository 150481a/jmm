package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 18:11
 * @Version 1.0
 */
public class TaskMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       ForkJoinPool pool = new ForkJoinPool(3);
       Future<Integer> future = pool.submit(new TaskTest(1, 1000));
       System.out.println("future:"+future.get());
    }
}
