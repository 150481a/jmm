package bamjun.test;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/5/22 16:27
 * @Version 1.0
 */
public class TestSync {

    static final Object o =new Object();
    static  int count;
    public  static void main(String[] args) throws Exception {
        synchronized (o) {
            count++;
        }
        System.out.println("count:"+count);

    }
}
