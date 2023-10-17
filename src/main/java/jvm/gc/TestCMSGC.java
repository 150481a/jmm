package jvm.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @program: jmm
 * @description: CMS垃圾回收器 demo
 * 设置串行收集器：-XX:+UseConcMarkSweepGC   指定年轻代老年代都是用并行行垃圾收集器
 * -XX:+PrintGCDetails  打印垃圾回收的详情信息
 * 测试串行垃圾回收器 将堆的内存初始和最大内存都设置为16M
 * -Xms16m  -Xmx16m
 * @Author: xiang
 * @create: 2023/7/3 11:46
 * @Version 1.0
 */
public class TestCMSGC {

    public static void main(String[] args) throws InterruptedException {

        List<Object> list = new ArrayList<Object>();
        while (true) {
            int sleep = new Random().nextInt(100);
            if(System.currentTimeMillis()%2==0){
                list.clear();
            }else{
                for (int i = 0; i < 10000; i++) {
                    Properties properties=new Properties();
                    properties.put("key"+i,"value_"+System.currentTimeMillis()+"-"+i);
                    list.add(properties);
                    
                }
            }
            System.out.println("list大小为:"+list.size());
            Thread.sleep(sleep);

        }
    }
}
