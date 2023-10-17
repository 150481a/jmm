package designpattern.Single;

/**
 * @program: jmm
 * @description: 单例模式
 * @Author: xiang
 * @create: 2023/6/6 20:48
 * @Version 1.0
 */
public class SingleDemo {

    private static SingleDemo singleDemo=new SingleDemo();
    private   SingleDemo(){

    }

    public static SingleDemo getSingleDemo(){
        return singleDemo;
    }


    public  void message(){
        System.out.println("hello");
    }


}
