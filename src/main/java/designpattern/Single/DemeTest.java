package designpattern.Single;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/6 21:01
 * @Version 1.0
 */
public class DemeTest {

    public static void main(String[] args) {
        List<SingleDemo2> st = new ArrayList<SingleDemo2>();
        SingleDemo2 singleDemo = SingleDemo2.getSingleDemo();

        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i <200;i++){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i<= 10900;i++){
                        SingleDemo2 singleDemo2 = SingleDemo2.getSingleDemo();
                        if(singleDemo!=singleDemo2){
                            st.add(singleDemo2);
                        }
                    }
                }
            });
            threads.add(thread);
        }
        threads.forEach(Thread::start);
        System.out.println("size:"+st.size());
    }
}
