package local;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/12 17:34
 * @Version 1.0
 */
public class BadLocal {

    public static void main(String[] args) throws IOException {
        ThreadLocal<Map> local = new ThreadLocal<>();//ThreadLocal 存的是引用地址， 使用时注意避免共享数据
        Map hashMap = new HashMap();
        int i=1;
       int l= i<<1;
       System.out.println(l);
        File file=new File("");
        FileInputStream  fileInputStream=new FileInputStream(file);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
        int read = bufferedInputStream.read();
        FileReader fileReader=new FileReader(file);
        int read1 = fileReader.read();
        FileWriter fileWriter=new FileWriter(file);


//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                hashMap.put("name","i am " +Thread.currentThread().getName());
//                local.set(hashMap);
//                System.out.println(Thread.currentThread().getName()+ ":"+local.get().get("name"));
//                try {
//                    Thread.sleep(3000);
//                }catch(Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+ ":"+local.get().get("name"));
//
//            }
//        }).start();
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                hashMap.put("name","i am " +Thread.currentThread().getName());
//                local.set(hashMap);
//                System.out.println("11");
//            }
//        }).start();

    }
}
