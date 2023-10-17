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
public class Hash {


    //定义存放服务器列表数组
    ArrayList<String> ips;

    /**
     * 初始化服务器列表
     * @param nodername
     */
    public Hash(String nodername){
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
     * 注意，这里和来访ip是有关系的吗，采用一个参数，表示当的来访ip
     */
    public void request(String ip){
        int i = hash(ip);
        System.out.println(ip+"--->"+ips.get(i));
    }

    /**
     * 添加服务器节点
     * 根据实际情况需要初始化时需要 预留一定空间
     * 添加节点会造成内部Hash重排， 为什么？
     * 这个问题在一致性hash中会进入详细探讨
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

    /**
     * 映射到key 算法，这里取余数做下标
     * @param ip
     * @return
     */
    private  int hash(String ip){
        Integer integer = Integer.valueOf(ip.substring(ip.lastIndexOf(".") + 1, ip.length()));
        return integer%ips.size();
    }

    public static void main(String[] args) throws InterruptedException {
        //初始化 两台服务器
        Hash hash = new Hash("192.168.0.1,192.168.0.2");
        //额外起线程模拟 不断请求
        for (int i = 0; i < 10; i++) {
            String ip="192.168.1."+i;
            hash.request(ip);
        }

        hash.addNode("192.168.0.3");
        //额外起线程模拟 不断请求
        for (int i = 0; i < 10; i++) {
            String ip="192.168.0."+i;
            hash.request(ip);
        }


    }
}
