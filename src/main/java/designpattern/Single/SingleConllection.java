package designpattern.Single;

/**
 * @program: jmm
 * @description: 单例模式
 * @Author: xiang
 * @create: 2023/6/6 20:51
 * @Version 1.0
 */
public class SingleConllection {

    public static void main(String[] args) {
       SingleDemo2 singleDemo=SingleDemo2.getSingleDemo();
       SingleDemo2 singleDemo1=SingleDemo2.getSingleDemo();
        singleDemo.message();
        singleDemo1.message();

        System.out.println(singleDemo==singleDemo1);
    }
}
