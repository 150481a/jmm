package map;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/13 15:36
 * @Version 1.0
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        for (int i = 0; i <5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    concurrentHashMap.put(UUID.randomUUID(),"1");
                }
            }).start();
        }
        Thread.sleep(1000);
        concurrentHashMap.values().stream().forEach(e->{
            System.out.println("e:"+e);
        });
        concurrentHashMap.entrySet().stream().forEach(e->{
            System.out.println("e1:"+e);
        });



    }
}
