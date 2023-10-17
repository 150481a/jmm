package jvm;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/11 16:07
 * @Version 1.0
 */
public class TestString {
    public static void main(String[] args) {
        new TestString().m1();
        new TestString().m2();
    }
    public void m1(){
        String s1="123";
        String s2="456";
        String s3=s1+s2;
        System.out.println(s3);
    }

    public void m2(){
        String s1="123";
        String s2="456";
        StringBuilder sb=new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        String s3 = sb.toString();
        System.out.println(s3);
    }
}
