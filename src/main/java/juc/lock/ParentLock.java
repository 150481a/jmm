package juc.lock;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/9 14:15
 * @Version 1.0
 */
public class ParentLock {
    byte[] lock=new byte[0];
    public  void f1(){
        synchronized(lock){
            System.out.println("f1 from parent");
        }
    }

}
