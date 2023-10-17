package algorithm.schedule;

/**
 * @program: jmm
 * @description: 任务类
 * @Author: xiang
 * @create: 2023/7/24 13:50
 * @Version 1.0
 */
public class Task {


    //任务名称
    private  String name;

    //任务提交时间
    private Long addTime;
    //任务执行时间长短
    private int servTime;

    //任务优先级
    private int level;

    public Task(String name, int servTime){

        this.name =name;
        this.servTime = servTime;
        this.addTime = System.currentTimeMillis();

    }

    public Task(String name, int servTime,int level){

        this.name =name;
        this.servTime = servTime;
        this.level=level;
        this.addTime = System.currentTimeMillis();

    }

    public void execute(){
        try {
            Thread.currentThread().sleep(servTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(String.format("execute:name=%s,level=%s,addTime=%s,servTime=%s",name,level,addTime,servTime));
    }


}
