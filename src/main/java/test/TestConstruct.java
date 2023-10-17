package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;
import org.openjdk.jcstress.infra.results.I_Result;

import java.util.Objects;

/**
 * @program: jmm
 * @description: 安全发布
 * @Author: xiang
 * @create: 2023/5/23 11:51
 * @Version 1.0
 */
public class TestConstruct {


    /**
     * 测试不安全构造
     */
    @JCStressTest
    @Outcome(id = {"16","-1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case1{
        Holder f;
        int v=1;

        @Actor
        public void actor1() {
            f=new Holder(v); //对象发布  3步操作  1 分配成员空间 2 调用构造方法 3 对象引用复制成员变量
        }

        @Actor
        public void actor2(I_Result r) {
            Holder o = this.f;
            if(Objects.isNull(o)){
                r.r1=-1;
            }else{
                r.r1=o.x1+o.x2+o.x3+o.x4+o.x5+o.x6+o.x7+o.x8;
                r.r1+=o.y1+o.y2+o.y3+o.y4+o.y5+o.y6+o.y7+o.y8;
            }
        }

        static class Holder{
            int x1,x2,x3,x4,x5,x6,x7,x8;
            int y1,y2,y3,y4,y5,y6,y7,y8;
            public Holder(int v){
                x1=v;x2=v;x3=v;x4=v;x5=v;x6=v;x7=v;x8=v;
                y1=v;y2=v;y3=v;y4=v;y5=v;y6=v;y7=v;y8=v;
            }
        }
    }

    /**
     * 使用final 构建
     */
    @JCStressTest
    @Outcome(id = {"16","-1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case2{
        Holder f;
        int v=1;

        @Actor
        public void actor1() {
            f=new Holder(v); //对象发布  3步操作  1 分配成员空间 2 调用构造方法 3 对象引用复制成员变量
        }

        @Actor
        public void actor2(I_Result r) {
            Holder o = this.f;
            if(Objects.isNull(o)){
                r.r1=-1;
            }else{
                r.r1=o.x1+o.x2+o.x3+o.x4+o.x5+o.x6+o.x7+o.x8;
                r.r1+=o.y1+o.y2+o.y3+o.y4+o.y5+o.y6+o.y7+o.y8;
            }
        }

        static class Holder{
            int x1,x2,x3,x4,x5,x6,x7;
            final int x8;
            int y1,y2,y3,y4,y5,y6,y7,y8;
            public Holder(int v){
                x1=v;x2=v;x3=v;x4=v;x5=v;x6=v;x7=v;x8=v;
                y1=v;y2=v;y3=v;y4=v;y5=v;y6=v;y7=v;y8=v;
            }
        }
    }

    /**
     * 使用volatile 构建
     */
    @JCStressTest
    @Outcome(id = {"16","-1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case3{
        Holder f;
        int v=1;

        @Actor
        public void actor1() {
            f=new Holder(v); //对象发布  3步操作  1 分配成员空间 2 调用构造方法 3 对象引用复制成员变量
        }

        @Actor
        public void actor2(I_Result r) {
            Holder o = this.f;
            if(Objects.isNull(o)){
                r.r1=-1;
            }else{
                r.r1=o.x8+o.x1+o.x2+o.x3+o.x4+o.x5+o.x6+o.x7;
                r.r1+=o.y1+o.y2+o.y3+o.y4+o.y5+o.y6+o.y7+o.y8;
            }
        }

        static class Holder{
            int x1,x2,x3,x4,x5,x6,x7;
            int y1,y2,y3,y4,y5,y6,y7,y8;
            volatile int x8;
            public Holder(int v){
                x1=v;x2=v;x3=v;x4=v;x5=v;x6=v;x7=v;
                y1=v;y2=v;y3=v;y4=v;y5=v;y6=v;y7=v;y8=v;
                x8=v;
            }
        }
    }
}
