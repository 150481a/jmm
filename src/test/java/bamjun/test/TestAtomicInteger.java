package bamjun.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/5/22 15:33
 * @Version 1.0
 */
public class TestAtomicInteger {

    //volatile 只能有序+可见 ，不能保证原子性
    // cas +volatile 保证原子性
    //比较并交换  乐观锁   线程数少于CPU核心数时  性能最高
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(0);//当前值

        while (true){
            int x=a.get();
            int y=x+1;
            //                 旧值  新值
            if(a.compareAndSet(x,   y)){
                break;
            }
        }
        System.out.println(a.get());
    }
}
