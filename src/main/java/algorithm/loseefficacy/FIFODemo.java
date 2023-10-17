package algorithm.loseefficacy;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @program: jmm
 * @description: 先进先淘汰算法    fifo   frist  in   frist out
 * @Author: xiang
 * @create: 2023/7/15 23:16
 * @Version 1.0
 */
public class FIFODemo {
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
        FIFODemo fifoDemo=new   FIFODemo();
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
