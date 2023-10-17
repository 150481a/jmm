package bamjun.test;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/5/23 15:23
 * @Version 1.0
 */
public class Singleton {
    private volatile   static  Singleton INSTANCE =null;

    public static Singleton getInstance(){
//        return INSTANCE=new Singleton();
        if(null==INSTANCE){
            synchronized (Singleton.class){
                if(null==INSTANCE){
                    INSTANCE=new Singleton();
                }
            }
        }
        return INSTANCE;
    }

}
