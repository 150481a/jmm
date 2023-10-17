package algorithm.schedule;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jmm
 * @description: 最小连接数
 * @Author: xiang
 * @create: 2023/7/27 18:15
 * @Version 1.0
 */
public class LC {


    //节点列表
    Node[] nodes;

    /**
     * 初始化堆 创建堆，
     * 因为开始时各节点链接数为0，所以直接填充数组即可
     * @param ns
     */
    public LC(String ns) {
        String[] nsl = ns.split(",");
        nodes = new Node[nsl.length + 1];
        for (int i = 0; i < nsl.length; i++) {
            nodes[i+1] = new Node(nsl[i]);
        }
    }

    /**
     * 节点下沉  与左右子节点比对，选取里面最小的交换
     * 目的是始终保持最小堆的顶点元素值最小
     * i 要下沉的顶点序号
     * @param i
     */
    public void down(int i){
        //顶点序号遍历，只需要到数组的一半即可，时间复杂度为O(log2n)
        while (i <<1 < nodes.length){
            //左子，左移一位
            int left = i << 1;
            //右子，左子+1即可
            int right= left + 1;
            //标记，指向 本节点，左、右子节点最小的，一开始取i自己
            int flag=i;

            //判断左子是否小于本节点
            if(nodes[left].get()<nodes[i].get()){
                flag=left;
            }

            //判断 右子是否小于本节点
            if(right< nodes.length&&nodes[flag].get()>nodes[right].get()){
                flag=right;
            }

            //两者中最小的与本节点不相等，则交换
            if(flag!=i){
               Node tmp= nodes[i];
               nodes[i]=nodes[flag];
               nodes[flag]=tmp;
               i=flag;
            }else break;  //否则相等，堆排序完成，退出循环即可
        }
    }

    /**
     *请求 直接取最小堆的堆顶元素就是连接数最小的机器
     */
    public  void request(){
        System.out.println("------------------>");
        //取堆顶元素响应请求
        Node node=nodes[1];
        System.out.println(node.name+"accept");
        //连接数加1
        node.inc();
        //排序前的堆
        System.out.println("before:"+ Arrays.toString(nodes));
        //堆顶下沉
        down(1);
        //排序后的堆
        System.out.println("after:"+ Arrays.toString(nodes));

    }

    public static void main(String[] args) {
        //假设有7台机器
        LC lc = new LC("a,b,c,d,e,f,g");
        //默认是个链接请求
        for (int i = 0; i < 10; i++) {
            lc.request();
        }
    }





    class Node{

        //节点标识
        String  name;

        //计数器
        AtomicInteger count=new AtomicInteger(0);

        public Node(String name){
            this.name = name;
        }

        /**
         * 计数器加1
         */
        public void inc(){
            count.getAndIncrement();
        }

        /**
         * 获取连接数
         * @return
         */
        public  int get(){
            return count.get();
        }

        @Override
        public String toString() {
            return name+"="+count;
        }
    }
}
