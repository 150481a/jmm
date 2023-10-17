package algorithm.loseefficacy;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @program: jmm
 * @description: Least Frequently Used,即最近最少使用，它要淘汰的是最近一段时间内，使用次数最少的值，可以认为比LRU多了一重判断，LFU需要时间次数两个维度的参考指标，需要注意的是，两个维度就可能设计同一时间段内，访问次数相同的情况，就必须内置一个计数器和队列，计数器算数，队列放置相同计数时的访问时间
 *
 * @Author: xiang
 * @create: 2023/7/15 23:16
 * @Version 1.0
 */
public class LFUDemo {
    private  LinkedList<Integer> fifo=new  LinkedList<Integer>();
     int size=3;

    public  void  add(int i){
        if(fifo.size()>=size){
            fifo.removeLast();
        }
        fifo.addFirst(i);
        print();

    }

    public  void read(int i){
        Iterator<Integer> iterator = fifo.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if(i==next){
                System.out.println("find it !!");
                print();
                return;
            }
        }
        System.out.println("not  found !");
        print();
    }


    public static void main(String[] args) {
        LFUDemo fifoDemo=new LFUDemo();
        System.out.println("add  fifo 1-3");
        fifoDemo.add(1);
        fifoDemo.add(2);
        fifoDemo.add(3);
        System.out.println("add fifi 4");
        fifoDemo.add(4);
        System.out.println("read  fifo 2");
        fifoDemo.read(2);
        System.out.println("read fifi 100");
        fifoDemo.read(100);
        System.out.println("add  fifo 5");
        fifoDemo.add(5);

    }



    private  void print() {
        System.out.println("fifo:"+fifo);
    }


}
