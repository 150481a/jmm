package opt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/14 17:21
 * @Version 1.0
 */
public class TotalLock {

    final  long start=System.currentTimeMillis();
    AtomicLong atomicLong=new AtomicLong(0);
    private Map<String,Long> map=new HashMap<String,Long>();
    ReentrantLock lock=new ReentrantLock();

    public Map read(){
        lock.lock();
        try {
            Thread.sleep(100);
        }catch(Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        map.put("time",end);
        lock.unlock();
        System.out.println(Thread.currentThread().getName()+",read="+(end-start));
        atomicLong.addAndGet(end-start);
        return map;
    }

    public Map write(){
        lock.lock();
        try {
            Thread.sleep(100);
        }catch(Exception e){
            e.printStackTrace();
        }
        map.put("count",null==map.get("count")? 0+1L:map.get("count")+1L);
        long end = System.currentTimeMillis();
        map.put("time",end);
        lock.unlock();
        System.out.println(Thread.currentThread().getName()+",write="+(end-start));
        atomicLong.addAndGet(end-start);
        return map;
    }

    public static void main(String[] args) throws InterruptedException {
        TotalLock totalLock = new TotalLock();

        for (int i = 0; i <=30; i++) {
            new Thread(()->{
                totalLock.read();
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                totalLock.write();
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(totalLock.map);
        System.out.println("总耗时时间为："+totalLock.atomicLong.get());
    }
 }
