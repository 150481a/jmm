package thread;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/7 15:31
 * @Version 1.0
 */
public class ThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("线程跑秋来了");
        });
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
    }
}
