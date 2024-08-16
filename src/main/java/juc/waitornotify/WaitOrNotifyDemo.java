package juc.waitornotify;

public class WaitOrNotifyDemo {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        synchronized (myThread){
            try{
                //线程启动
                myThread.start();
                //主线程休眠
                Thread.sleep(3000);
                System.out.println("before wait");
                //线程阻塞
                myThread.wait();
                System.out.println("after wait");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        synchronized (this){
            System.out.println("before notify");
            notify();
            System.out.println("after notify");
        }
    }
}
