package busi;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/19 11:02
 * @Version 1.0
 */
public class JoinTest implements  Runnable{

    @Override
    public void run() {
        System.out.println("我是子线程");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread t1 = new Thread(new JoinTest());
        t1.start();
        t1.join();
        System.out.println("end");
    }
}
