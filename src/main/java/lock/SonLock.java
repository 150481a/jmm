package lock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/9 14:17
 * @Version 1.0
 */
public class SonLock extends ParentLock{

    public void f1(){

        //synchronized(SonLock.class)  类级别锁
        //对象锁
        synchronized(super.lock){
            super.f1();
            System.out.println("f1 from son");
        }
    }

    public static void main(String[] args) {
        SonLock sonLock=new SonLock();
        sonLock.f1();
    }
}
