package algorithm.schedule;

import java.util.Random;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/24 15:11
 * @Version 1.0
 */
public class RR {

    //定义数组作为插槽，每个插槽中可以放入任务
    Integer [] integers;


    /**
     *
     * @param length 为插槽个数
     */
    public RR(int length){
        integers=new Integer[length];
    }


    /**
     *
     * @param value 将任务放入插槽
     */
    public void addTask(int value) {
        //插槽下标
        int slot=0;
        //不停查找空的插槽
        while(true){
            //发现空的插槽 放入任务
            if(null==integers[slot]){
                integers[slot]=value;
                System.out.println(String.format("-----------------> add task index=%s,value=%s",slot,value));
                break;
            }
            //当前插槽被占用  继续寻找下一个插槽
            slot++;
            //如果插槽遍历完还是没有空的位置，那么晚从头开始再找，开启下一个轮混
            if(slot==integers.length){
                slot=0;
            }
        }
    }

    /**
     * 执行任务  ，轮询的策略就在这里
     */

    public void execute(){
        //开启线程处理任务，在现实中可能有多个消费者处理
        new Thread(()->{
            //定义消费指针
            int index=0;
            while (true){
                //指针轮询，如果达到尾部，下一步重新转向开头
                //数据物理结构是一个数组，逻辑上是一个环
                if(index==integers.length){
                    index=0;
                }
                //如果当前位置没有任务，轮询到下一个插槽
                if(null==integers[index]){
                    index ++;
                    continue;
                }else{
                    try {
                        //随机等待，表示模拟当前任务有一个执行时间
                        Thread.currentThread().sleep(new Random().nextInt(1000));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //模拟执行任务的内容，也就是打印一下当前插槽和里面的值
                    System.out.println(String.format("execute index=%s,value=%s",index,integers[index]));
                    //执行完 将当前的插槽置空，腾出位置来给后续任务使用
                    integers[index] =null;
                }
            }
        }).start();
    }

    public static void main(String[] args) {
       //初始化定义 5个插槽
        RR rr = new RR(5);
        //唤起执行中线程，开始轮询
        rr.execute();
        //放置任务
        for(int i = 0; i <10;i++){
            rr.addTask(i);
        }
    }


}
