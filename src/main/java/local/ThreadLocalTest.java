package local;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 17:19
 * @Version 1.0
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(100);
        System.out.println("threadLocal:"+threadLocal.get());
        System.gc();
        // 不会被回收 因为threadLocal 被强制引用
        System.out.println(threadLocal.get());
        threadLocal=null;
        //debug 查看currentThread你里面的localMaps
        //注意table里的reference
        Thread thread = Thread.currentThread();
        //断电1  虽然local 被赋值null,但是ThreadLocal内部依然存在引用(有  内存泄露溢出风险)
        System.out.println("1");
        System.gc();
        // 断点2   再次GC后，引用消失
        System.out.println("2");
    }
}
