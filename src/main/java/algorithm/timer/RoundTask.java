package algorithm.timer;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/24 22:04
 * @Version 1.0
 */
public class RoundTask {

    //延迟多少秒后执行
    int delay;

    //加入的序列号，只是标记下加入的顺序
    int index;

    public RoundTask(int index, int delay){
        this.delay = delay;
        this.index = index;
    }

    void run(){
        System.out.println(String.format("task=%s,index=%s",delay,index));
    }

    @Override
    public String toString() {
        return String.valueOf(index+"="+delay);
    }


}
