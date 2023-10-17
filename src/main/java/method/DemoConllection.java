package method;

import method.service.DemoService;
import method.service.impl.DemoServiceImpl;

/**
 * @program: jmm
 * @description: java 1.8以上优化
 * @Author: xiang
 * @create: 2023/6/5 16:32
 * @Version 1.0
 */
public class DemoConllection {


    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        System.out.println("demoser:"+demoService.deaMethod());

        System.out.println("demoser:"+ DemoService.staticMethod());

        DemoService demoService1=new DemoService() {
            @Override
            public String abstractMethod() {
                return "exec deoversion";
            }

            @Override
            public String deaMethod() {
                return "DemoService.super.deaMethod()";
            }
        };
        System.out.println("demoser1:"+demoService1.abstractMethod());

        System.out.println("demoser1:"+demoService1.deaMethod());
    }
}
