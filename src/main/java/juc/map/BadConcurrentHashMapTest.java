package juc.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/13 15:36
 * @Version 1.0
 */
public class BadConcurrentHashMapTest {
    public static  void main(String[] args) throws InterruptedException {
        Map<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();
//        Hashtable<String, Integer> concurrentHashMap = new Hashtable<>();
        concurrentHashMap.put("val",0);
        for (int i = 0; i <20;i++){
            new Thread(()-> {
                int  o = concurrentHashMap.get("val");
                o++;
                try {
                    Thread.sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
                concurrentHashMap.put("val",o);

            }).start();
        }
        Thread.sleep(4000);
        System.out.println("val:"+concurrentHashMap.get("val"));
    }
}
