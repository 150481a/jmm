package keywords;

/**
 * 写final域重排序规则写final域的重排序规则禁止对final域的写重排序到构造函数之外，这个规则的实现主要包含了两个方面：JMM禁止编译器把final域的写重排序到构造函数之外；编译器会在final域写之后，构造函数return之前，插入一个storestore屏障。这个屏障可以禁止处理器把final域的写重排序到构造函数之外。我们再来分析writer方法，虽然只有一行代码，但实际上做了两件事情：构造了一个FinalDemo对象；把这个对象赋值给成员变量finalDemo。
 * 读final域重排序规则读final域重排序规则为：在一个线程中，初次读对象引用和初次读该对象包含的final域，JMM会禁止这两个操作的重排序。(注意，这个规则仅仅是针对处理器)，处理器会在读final域操作的前面插入一个LoadLoad屏障。实际上，读对象的引用和读该对象的final域存在间接依赖性，一般处理器不会重排序这两个操作。但是有一些处理器会重排序，因此，这条禁止重排序规则就是针对这些处理器而设定的。read()方法主要包含了三个操作：初次读引用变量finalDemo;初次读引用变量finalDemo的普通域a;初次读引用变量finalDemo的final域b;
 */
public class FinalDemo {


    //普通域
    private  int a ;

    // final  域
    private final int b;

    private static FinalDemo finalDemo;

    public FinalDemo(){
        System.out.println("1");
        a=1;
        b=2;
    }
    public static void writer(){
        System.out.println("2");
        finalDemo=new FinalDemo();
    }

    public static void reader(){
        System.out.println("3");
        FinalDemo demo = finalDemo;
        System.out.println("4");
        int a = demo.a;
        int b = demo.b;
        System.out.println("a="+a);
        System.out.println("b="+b);
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            FinalDemo.writer();
        }).start();

        Thread.sleep(3);

        new Thread(()->{
            FinalDemo.reader();
        }).start();
    }
}
