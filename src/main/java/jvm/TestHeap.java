package jvm;

import jvm.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmm
 * @description:  jvm  调优参数   加上参数查看jvm回收 -Xms 8m -XX:+HeapDumpOnOutOfMemoryError
 * @Author: xiang
 * @create: 2023/6/30 10:40
 * @Version 1.0
 */
public class TestHeap {


    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        while (true){

            new Thread(()->{
                User user = new User();
                user.setId(1L);
                user.setUsername("user");
                user.setPassword("pass");
                if(System.currentTimeMillis()%2==0){
                    users.add(user);
                    System.out.println("add to lists,size="+users.size());
                }
            }).start();
            new Thread(()->{
                User user = new User();
                user.setId(1L);
                user.setUsername("user");
                user.setPassword("pass");
                if(System.currentTimeMillis()%2==0){
                    users.add(user);
                    System.out.println("add to lists,size="+users.size());
                }
            }).start();

            new Thread(()->{
                User user = new User();
                user.setId(1L);
                user.setUsername("user");
                user.setPassword("pass");
                if(System.currentTimeMillis()%2==0){
                    users.add(user);
                    System.out.println("add to lists,size="+users.size());
                }
            }).start();

            new Thread(()->{
                User user = new User();
                user.setId(1L);
                user.setUsername("user");
                user.setPassword("pass");
                if(System.currentTimeMillis()%2==0){
                    users.add(user);
                    System.out.println("add to lists,size="+users.size());
                }
            }).start();


            try {Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
        }
    }

}
