package bamjun.test;

import java.util.Arrays;
import java.util.List;

/**
 * @program: jmm
 * @description: 加加减减问题  --原子性问题
 * @Author: xiang
 * @create: 2023/5/3 23:56
 * @Version 1.0
 */
public class TestAddSub {
    static  int balance = 10;
   public static void withdrw(){
       balance +=5;
   }
   public static  void deposit(){
       balance -=5;
   }

    public static void main(String[] args) {
        List<Thread> threads = Arrays.asList(
                new Thread(TestAddSub::deposit),
                new Thread(TestAddSub::withdrw));
        threads.forEach(Thread::start);
        threads.forEach(e->{
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println("balance="+balance);



    }



}
