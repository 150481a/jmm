package jvm;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/11 15:17
 * @Version 1.0
 */
public class MyDemo {

    private     int a=3;
    private     int b=4;

    public int getSum() {
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        MyDemo myDemo = new MyDemo();
        int sum = myDemo.getSum();
        System.out.println(sum);
    }
}
