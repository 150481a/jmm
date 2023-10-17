package algorithm.loseefficacy;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/17 17:50
 * @Version 1.0
 */
public class Dto implements  Comparable<Dto> {

    private Integer key;

    private int count;

    private long lastTime;


    public Dto(Integer key, int count,long lastTime) {
        this.key = key;
        this.count = count;
        this.lastTime = lastTime;
    }

    @Override
    public int compareTo(Dto o) {
        int compare = Integer.compare(this.count, o.count);
        return compare==0?Long.compare(this.lastTime,o.lastTime):compare;
    }

    @Override
    public String toString() {
        return String.format("[key=%s,count=%s,lastTime=%s]",key,count,lastTime);
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}
