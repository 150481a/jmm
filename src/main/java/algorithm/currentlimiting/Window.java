package algorithm.currentlimiting;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jmm
 * @description: 时间滑动窗口算法
 * @Author: xiang
 * @create: 2023/7/24 12:45
 * @Version 1.0
 */
public class Window {

    //定义整个窗口的流量上限，超出会被拒绝
    final Integer totalMax=7;

    //定义每一片流量上限，超出同样会被拒绝，可以设置不同的值，注意点每一片的流量上限需要小于整个窗口的流量上限，否则无意义，
    final Integer sliceCount=5;

    //定义分片数量
    final Integer slice=3;

    //定义整个窗口，分3段，每段1s,也就是总长度3s
    final LinkedList<Long> linkList=new LinkedList<Long>();

    //计数器，每一片一个key,可以使用HashMap,这里为了控制台保持有序性和可读性，采用TreeMap,
    Map<Long, AtomicInteger> map=new TreeMap<Long, AtomicInteger>();

    //心跳，每1s跳动一次，滑动窗口向前滑动一步，实际业务中可能需要手动控制滑动窗口的时机
    ScheduledExecutorService service=Executors.newScheduledThreadPool(1);

    //获取Key值，这里即时时间戳(秒)
    private Long getKey(){
        return System.currentTimeMillis()/1000;
    }

    //初始化
    public Window(){

        Long key = getKey();
        //初始化窗口，当前时间指向最前端，前两片其实是过去的2s
        for(int i=0;i<slice;i++){
            linkList.addFirst(key-i);
            map.put(key,new AtomicInteger());
        }
        //启动心跳任务，窗口根据时间，自动向前滑动，每1秒一步
        service.scheduleAtFixedRate(new Runnable(){

            @Override
            public void run() {
               Long key1 = getKey();
               //队首添加最新的片
               linkList.addLast(key1);
               map.put(key1,new AtomicInteger());

               //移除最老的片
               map.remove(linkList.getFirst());
               linkList.removeFirst();

               System.out.println("step:"+key+":"+map);
            }
        }, 1000,1000 , TimeUnit.MILLISECONDS);
    }

    //检查每一片的流量是否超过上限
    public  Boolean checkCurrentSlices(){
        Long key = getKey();
        AtomicInteger atomicInteger = map.get(key);
        if(null!=atomicInteger ){
            return atomicInteger.get()<sliceCount;
        }

        return Boolean.TRUE;
    }

    //检查整个窗口的流量上限
    public  Boolean checkTotalMax(){
        return  map.values().stream().mapToInt(e->e.get()).sum()<totalMax;
    }


    //模拟请求来临
    public  void req(){

        //获取当前时间key
        Long key = getKey();

        //如果时间窗口未到达当前时间片，稍微等一下
        //其实是一种保护措施，放置心跳对滑动窗口的推动滞后于当前请求
        while (linkList.getLast()<key){
            try {
                Thread.sleep(200);
            }catch (Exception e){e.printStackTrace();}
        }

        //开始检查， 默认允许访问，
        //如果任意一片时间片的流量上限未达到上限值，返回ok,计数器值＋1
        //如果任意一项达到上限，拒绝请求，达到限流目的
        //这里是直接拒绝，实际代码中可能会设置缓存池，将请求放进缓冲队列中。
        if(checkCurrentSlices()&&checkTotalMax()){
            map.get(key).incrementAndGet();
            System.out.println("key:"+key+"=ok:"+map);
        }else System.out.println("key:"+key+"=reject:"+map);

    }

    public static void main(String[] args) throws InterruptedException {

        Window window = new Window();

        //模拟十个请求，相对之间200毫秒，会造成总数达到上限而被限流
        for (int i = 0; i <10;i++){
            Thread.sleep(200);
            window.req();
        }

        //等待时间窗口滑动
        Thread.sleep(3000);
        //模拟突发请求。单片计数器达到上限而被限流
        System.out.println("分割线~~~~~~~~~~~~~~");
        for (int i = 0;i<10;i++){
            window.req();
        }

    }



}
