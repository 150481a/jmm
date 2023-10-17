package algorithm.balance;

import java.util.ArrayList;
import java.util.Random;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/25 16:46
 * @Version 1.0
 */
public class Rand {


    //定义存放服务器列表数组
    ArrayList<String> ips;

    /**
     * 初始化服务器列表
     * @param nodername
     */
    public Rand(String nodername){
        System.out.println("init list:"+nodername);
        String[] nodes = nodername.split(",");
        //初始化服务器列表，长度取机器数
        ips = new ArrayList<>(nodes.length);
        for (String node:nodes) {
            ips.add(node);
        }
    }

    /**
     * 请求
     */
    public void request(){
        //下标随机取机器数量范围内数字
        int i = new Random().nextInt(ips.size());
        System.out.println(ips.get(i));
    }

    /**
     * 添加服务器节点  需要注意添加节点会造成内部数据数组扩容
     * 根据实际情况需要初始化时需要 预留一定空间
     * @param nodename
     */
    public void addNode(String nodename){
        System.out.println("add node :"+nodename);
        ips.add(nodename);
    }

    /**
     * 移除  直接从服务器列表删除数据
     * @param nodename
     */
    public void remove(String nodename){
        System.out.println("remove node :"+nodename);
        ips.remove(nodename);
    }

    public static void main(String[] args) throws InterruptedException {
        //初始化 两台服务器
        Rand rand = new Rand("192.168.0.1,192.168.0.2");
        //额外起线程模拟 不断请求
        new Thread(()->{
        while (true){
            try {Thread.sleep(500);}catch(Exception e){e.printStackTrace();}
            rand.request();
        }
        }).start();
        //3s后，3号机器加入服务器列表清单
        Thread.currentThread().sleep(3000);
        rand.addNode("192.168.0.3");

        //3s后移除 当前服务器节点
        Thread.currentThread().sleep(3000);
        rand.remove("192.168.0.2");


    }
}
