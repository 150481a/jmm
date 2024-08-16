package juc.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 本程序先执行park，然后在执行unpark，进行同步，并且在unpark的前后都调用了getBlocker，可以看到两次的结果不一样，并且第二次调用的结果为null，这是因为在调用unpark之后，执行了Lock.park(Object blocker)函数中的setBlocker(t, null)函数，所以第二次调用getBlocker时为null。
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");

        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }
}

class MyThread extends Thread {
    private Object object;

    public MyThread(Object o) {
        this.object = o;
    }

    @Override
    public void run() {
        System.out.println("before unpark");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //获取blocker
        System.out.println("Blocker info"+ LockSupport.getBlocker((Thread) object));
        //释放 许可
        LockSupport.unpark((Thread) object);

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e ){
            e.printStackTrace();
        }

        System.out.println("Blocker info "+ LockSupport.getBlocker((Thread) object));
        System.out.println("after unpark");

    }
}
