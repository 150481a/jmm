package bamjun.test;

import org.openjdk.jcstress.infra.results.II_Result;

import java.util.Arrays;
import java.util.List;

/**
 * @program: jmm
 * @description: 安全发布
 * @Author: xiang
 * @create: 2023/5/4 0:15
 * @Version 1.0
 */
public class HolderDemo {

    static int a,b;
    static II_Result r=new II_Result();

    public static void main(String[] args) {

        List<Thread> threads = Arrays.asList(new Thread(() -> {
            b = 1;
            r.r2 = a;

        }), new Thread(() -> {
            a = 2;
            r.r1 = b;
        }));
        threads.forEach(Thread::start);
        threads.forEach(e->{
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println("r.r1:"+r.r1);
        System.out.println("r.r2:"+r.r2);
    }

}
