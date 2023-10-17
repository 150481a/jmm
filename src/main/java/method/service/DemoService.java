package method.service;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/5 16:27
 * @Version 1.0
 */
public interface DemoService {

    //抽象方法
    String abstractMethod();

    //默认方法 扩展方法
    default String deaMethod() {
        return "exec deaMethod";
    }

    //静态方法 扩展方法
    static String staticMethod() {
        return "staticMethod";
    }



}
