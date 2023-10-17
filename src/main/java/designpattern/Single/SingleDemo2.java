package designpattern.Single;

/**
 * @program: jmm
 * @description: 饿汉模式
 * @Author: xiang
 * @create: 2023/6/6 20:48
 * @Version 1.0
 */
public class SingleDemo2 {

    private static SingleDemo2 singleDemo=null;
    private SingleDemo2(){

    }

    public static SingleDemo2 getSingleDemo(){
        if(null==singleDemo)
        {
            synchronized (SingleDemo2.class){
                if(null==singleDemo){
                    singleDemo=  new  SingleDemo2();
                }
            }
        }
        return singleDemo;
    }


    public  void message(){
        System.out.println("hello");
    }


}
