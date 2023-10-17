package bamjun.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/5/23 11:33
 * @Version 1.0
 */
public class TestJol {

   public static void main(String[] args) throws Exception {

       String printable = ClassLayout.parseClass(Test1.class).toPrintable();
       System.out.println("printable:"+printable);
   }

   public static class Test{
       private byte a,b,c;
       private long d;

   }

    public static class Test1{
        private byte a,c,d;
        private int b;

    }

    public static class Test2{
        private byte a,c,d;
        private boolean b;

    }

    public static class Test3{
        private long a,c,d;
        private double b;

    }

    public static class Test4{
        private char a,c,d;
        private double b;

    }

    public static class Test5{
        private String a,c,d;
        private double b;

    }
}
