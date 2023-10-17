package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/1 16:13
 * @Version 1.0
 */
public class TestJvmOutOfMemory {


    public static void main(String[] args) {
        List<Object> list= new ArrayList<Object>();
        for (int i=0; i<1000000000; i++){
            String  str="";
            for (int j = 0; j <1000; j++) {
                str+= UUID.randomUUID().toString();
            }
            list.add(str);
        }
        System.out.println("ok");

    }
}
