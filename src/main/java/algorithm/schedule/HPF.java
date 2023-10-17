package algorithm.schedule;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @program: jmm
 * @description: 优先级调度
 * @Author: xiang
 * @create: 2023/7/24 14:17
 * @Version 1.0
 */
public class HPF {

    public static void main(String[] args) {
        //有序map,将服务时间作为key排序
        final TreeMap<Integer,Task> map=new TreeMap<>();

        //向队列中放任务
        for (int i=0; i<10; i++) {
            System.out.println("add task"+i);
            int servTime = new Random().nextInt(1000);
            //注意，key是servTime,即执行时间
            map.put(i,new Task("task"+i,servTime,i));
        }
        //服务线程，任务由该线程获取和执行
        new Thread(()->{
            while (true){
                try {
                    //有序map中，服务时间短的，置于顶部、那么自然就会优先被去除
                    Map.Entry<Integer, Task> integerTaskEntry = map.pollLastEntry();
                    if(null==integerTaskEntry){
                        Thread.currentThread().sleep(100);
                    }else integerTaskEntry.getValue().execute();
                }catch (Exception e){e.printStackTrace();}

            }
        }).start();

    }
}
