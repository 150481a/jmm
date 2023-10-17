package algorithm.timer;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jmm
 * @description: 时间轮demo
 * @Author: xiang
 * @create: 2023/7/24 22:58
 * @Version 1.0
 */
public class RoundDeme {

    //定义小轮 插槽数
    int size1=10;

    //定义大轮的插槽数
    int size2=5;

    //定义小轮数组，每个元素是一个链表
    LinkedList<RoundTask>[] t1=new LinkedList[size1];

    //定义大轮数组，每个元素是一个链表
    LinkedList<RoundTask>[] t2=new LinkedList[size2];

    //小轮计数器，指针跳动的格数，每秒加1
    final AtomicInteger  flag1=new AtomicInteger();

    //大轮计数器，指针跳动的格数，每10s加1
    final AtomicInteger  flag2=new AtomicInteger();

    //调度器，拖动指针跳动  定义两个线程
    ScheduledExecutorService service=Executors.newScheduledThreadPool(2);

    /**
     * 初始化时间轮
     */
    public RoundDeme(){
        for(int i=0;i<size1;i++){
            t1[i]=new LinkedList<>();
        }
        for(int i=0;i<size2;i++){
            t2[i]=new LinkedList<>();
        }
    }

    /**
     * 打印时间轮的结构  数组加链表
     */
    public void print(){
        System.out.println("t1:");
        for(int i=0;i<t1.length;i++){
            System.out.println(t1[i]);
        }

        System.out.println("t2:");
        for(int i=0;i<t2.length;i++){
            System.out.println(t2[i]);
        }
    }


    /**
     * 添加任务到时间轮
     * @param task  任务
     */
    public void addTask(RoundTask task){
        //获取任务的 执行时间
        int delay = task.delay;
        System.out.println("delay:"+delay);
        if(delay<size1){
            //10秒以内 在小轮，槽取余数
            t1[delay].addLast(task);
        }else{
            //超过小轮的任务加入大轮，槽数除以小轮的槽数
            t2[delay/size1].addLast(task);
        }
    }


    /**
     * 小轮执行 推动时间前进
     */
    public void start1(){
        //每秒执行一次，推动时间轮旋转，渠道任务立马执行
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //获取小轮计数器 ，取余槽数，获取下槽数下标
                int point= flag1.getAndIncrement()%size1;
                System.out.println("t1----->slot"+point);
                //获取任务
                LinkedList<RoundTask> roundTasks = t1[point];
                //存在任务  取出来，依次执行吗，执行完后移除
                if(!roundTasks.isEmpty()){
                    while(roundTasks.size()!=0){
                        //执行任务完后 进行任务移除
                        roundTasks.getFirst().run();
                        roundTasks.removeFirst();
                    }
                }
            }
        },0,1, TimeUnit.SECONDS);
    }


    /**
     * 大轮执行 推动时间前进
     */
    public void start2(){
        //每10s执行一次，推动时间轮旋转，渠道任务立马执行
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int point= flag2.getAndIncrement()%size2;
                System.out.println("t2----->slot"+point);
                LinkedList<RoundTask> roundTasks = t2[point];
                if(!roundTasks.isEmpty()){
                    //存在任务 放进小轮中，如果当前槽内有任务，取出来，依次执行吗，执行完后移除
                    while(roundTasks.size()!=0){
                        RoundTask first = roundTasks.getFirst();
                        //放入小轮后 就将大轮 任务移除 ，小轮的槽数按10去余数
                        t1[first.delay%size1].addLast(first);
                        //从大轮移除
                        roundTasks.removeFirst();
                    }
                }
            }
        },0,10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        RoundDeme roundDeme=new RoundDeme();
        //模拟将一百个任务放入时间轮中  以随机数作为执行时间
        for(int i=0; i<100;i++){
            roundDeme.addTask(new RoundTask(i,new Random().nextInt(50)));
        }
        //打印所有任务  大小轮存放的展示
        roundDeme.print();
        //启动大轮
        roundDeme.start2();
        //启动小轮
        roundDeme.start1();


    }


}
