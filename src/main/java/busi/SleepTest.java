package busi;

/**
 * @program: jmm
 * @description: sleep   不释放锁  只是让出cpu执行权
 * @Author: xiang
 * @create: 2023/6/19 10:26
 * @Version 1.0
 */
public class SleepTest {

    public static void main(String[] args) {
        byte[] lock = new byte[0];

        new Thread(()->{
            synchronized (lock){
                System.out.println("start");
                try {
                    Thread.sleep(3000);
                }catch (Exception e){e.printStackTrace();}
                System.out.println("end");
            }

        }).start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("need lock");
            }
        }).start();

        new Thread(()->{
                System.out.println("sub");
        }).start();
    }
}
