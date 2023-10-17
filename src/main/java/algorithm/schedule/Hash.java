package algorithm.schedule;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: jmm
 * @description: 一致性hash值算法
 * @Author: xiang
 * @create: 2023/7/28 0:33
 * @Version 1.0
 */
public class Hash {


    //定义服务器列表
    private static String[] servers={"192.168.0.1","192.168.0.2","192.168.0.3","192.168.0.4"};
    //key 表示服务器的hash值，value表示服务器地址
    private static SortedMap<Integer,String> serverMap=new TreeMap<>();

    static {
        for (int i=0;i<servers.length;i++){
           int hash= getHash(servers[i]);
           //理论上，hash环的最大值为2^32
           //这里作实例，将ip尾部作为上限也就是254，
           //那么服务器0-4，乘以60后可以均匀分布到0~254的环上去
           //实际的请求ip到来时，在环上查找即可
           hash*=60;
           System.out.println("add "+servers[i]+", hash="+hash);
           serverMap.put(hash,servers[i]);
        }

    }

    /**
     * 根据hash值查找节点
     * @param key
     * @return
     */

    private  static  String getServer(String key){
        int hash = getHash(key);
        //得到大于该hash值的所有server
        SortedMap<Integer, String> subMap = serverMap.tailMap(hash);
        if(subMap.isEmpty()){
            //如果没有比该kash值大的 ，则从第一个node开始
            Integer i = serverMap.firstKey();
            return  serverMap.get(i);
        }else{
            //第一个key就是顺时针过去离node最近的那个节点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            return  subMap.get(i);
        }
    }

    /**
     * 运算hash值
     * 该函数可以自定义，只需要做到取值离散即可
     * 这里取ip地址的最后一节
     * @param server
     * @return
     */
    private static int getHash(String server) {
        String last = server.substring(server.lastIndexOf(".") + 1, server.length());
        return Integer.valueOf(last);
    }

    public static void main(String[] args) {
       //模拟5个请求
        for (int i = 0; i <8; i++) {
            String ip="192.168.1."+i*30;
            System.out.println(ip+"------------>"+getServer(ip));
        }
        //将5号服务器加到2~3之间，取中间位置，150

        System.out.println("add 192.168.0.5,hash=150");
        serverMap.put(150,"192.168.0.5");
        //再次发起5个请求
        for (int i = 0; i <8; i++) {
            String ip="192.168.1."+i*30;
            System.out.println(ip+"------------>"+getServer(ip));
        }

        for (int i = 0; i <8; i++) {
            String ip="192.168.1."+i*30;
            if(i==5){

            }
            System.out.println(ip+"------------>"+getServer(ip));
        }

    }
}
