package juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 18:01
 * @Version 1.0
 */
public class TaskTest extends RecursiveTask<Integer> {
    final int segment=100;//定义段值

    private int start,end;//定义临界值
    public TaskTest(int start,int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if(end-start>segment){
            int middle=(start+end)/2;
            TaskTest task1 = new TaskTest(start, middle);
            TaskTest task2 = new TaskTest(middle + 1, end);
            task1.fork();
            task2.fork();
            System.out.println("fork:"+start+","+middle+"#"+(middle+1)+","+end);
            return task1.join()+task2.join();

        }else{
          int i=0;
          for(int j=start; j<=end; j++){
              i+=j;
          }
          return i;
        }
    }


}
