package local;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 11:32
 * @Version 1.0
 */
public class ThreadLocalDemo implements  Runnable {
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            threadLocal.set(i);
            System.out.println(Thread.currentThread().getName()+",value="+threadLocal.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        new Thread(threadLocalDemo).start();
        new Thread(threadLocalDemo).start();
        new Thread(threadLocalDemo).start();
        new Thread(threadLocalDemo).start();
    }
}
