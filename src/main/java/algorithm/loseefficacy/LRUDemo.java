package algorithm.loseefficacy;

import java.util.*;

/**
 * @program: jmm
 * @description: 最久未用淘汰算法   Least Recently Used ，即淘汰最后一次使用时间最久远的数值，FIFO非常的粗暴，不管有没有用到，直接剔除时间最久远的元素，儿LRU认为，最近频繁使用过的数据，将来也很大程度被频繁用到，故而淘汰那些懒惰的数据，LinkedHashMap,数据，链表均可实现LRU,下面任然以链表为例，新加入的数据放在头部，最近访问的，也移动到头部，空间，满时，将尾部数据移除
 * @Author: xiang
 * @create: 2023/7/15 23:16
 * @Version 1.0
 */
public class LRUDemo {
    private Map<Integer, Integer> cache=new HashMap<Integer, Integer>(16);
    private Map<Integer,Dto> count=new HashMap<Integer,Dto>(16);
    int size = 3;

    public void put(Integer key, Integer value) {
        Integer integer = cache.get(key);
        if(null==integer){
            if(cache.size()==size){
                removeElement();
            }
            count.put(key,new Dto(key,1,System.currentTimeMillis()));
        }else{
            addCount(key);
        }
        cache.put(key,value);
    }

    private void addCount(Integer key) {
        Dto dto = count.get(key);
        dto.setCount(dto.getCount() + 1);
        dto.setLastTime(System.currentTimeMillis());

    }

    private void removeElement() {
        Dto min = Collections.min(count.values());
        cache.remove(min.getKey());
        count.remove(min.getKey());

    }

    public Integer get(Integer key) {
        Integer integer = cache.get(key);
        if(null!=integer){
            addCount(key);
            return integer;
        }
       return integer;
    }






    public static void main(String[] args) {
        LRUDemo fifoDemo = new LRUDemo();
        System.out.println("add  fifo 1-3");
        fifoDemo.put(1,1);
        fifoDemo.put(2,2);
        fifoDemo.put(3,3);
        fifoDemo.print();

        System.out.println("read   1  2");
        fifoDemo.get(1);
        fifoDemo.get(2);
        fifoDemo.print();


        System.out.println("add fifi 4");
        fifoDemo.put(4,4);
        fifoDemo.print();
        System.out.println("read   2  4");
        fifoDemo.get(2);
        fifoDemo.get(4);
        fifoDemo.print();

        System.out.println("add  fifo 5");
        fifoDemo.put(5,5);
        fifoDemo.print();
    }


    private void print() {
        System.out.println("cache:" + cache);
        System.out.println("count:" + count);
    }


}
