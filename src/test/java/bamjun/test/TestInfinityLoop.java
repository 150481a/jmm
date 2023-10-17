package bamjun.test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: jmm
 * @description: 永不终止的循环  --可见性问题
 * @Author: xiang
 * @create: 2023/5/3 23:26
 * @Version 1.0
 */
public class TestInfinityLoop {

    static volatile Boolean  stop=false;  //volatile  write 与volatile read边界也会触发Happens-before
//    static  Object  lock=new Object();
    public static void main(String[] args) throws InterruptedException {

       Thread  t= new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //synchronized (lock){  unlock与lock边界也会触发Happens-before
                stop=true;  //volatile  write  发生在volatile read 之前也会触发Happens-before
            //} 解锁  解锁比加锁先执行 就会触发Happens-before

        });
       System.out.println("start"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
       t.start();
       t.join();  //线程的结束和join()边界  会触发Happens-before
       foo();

    }

    private static void foo() {
        while (true){
           // synchronized (lock){    加锁
                boolean b=stop; //volatile read 读
                if(b){
                    break;
                }
           // }
        }
        System.out.println("end"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }


}
