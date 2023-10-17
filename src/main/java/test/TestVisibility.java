package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.III_Result;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * @program: jmm
 * @description: volatile 可见性
 * @Author: xiang
 * @create: 2023/5/22 11:10
 * @Version 1.0
 */
public class TestVisibility {

    /**
     * 案例1 测试对同一个变量的多次读写操作是否连贯
     */

    @JCStressTest
    @Outcome(id = {"3, 3, 3", "0, 0, 0"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = {"0, 3, 3", "0, 0, 3"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "0, 3, 0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case1 {
        static class Foo {
            int x = 0;
        }

        Foo p = new Foo();
        Foo q = p;

        @Actor
        public void actor1(III_Result r) {
            r.r1 = p.x;
            r.r2 = q.x;
            r.r3 = p.x;
        }

        @Actor
        public void actor2() {
            p.x = 3;
        }


    }

    /**
     * 案例2 测试对 加了  volatile 变量的多次读写操作是否连贯
     */
    @JCStressTest
    @Outcome(id = {"3, 3, 3", "0, 0, 0"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = {"0, 3, 3", "0, 0, 3"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @Outcome(id = "0, 3, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case2 {
        static class Foo {
            volatile int x = 0;
        }

        Foo p = new Foo();
        Foo q = p;

        @Actor
        public void actor1(III_Result r) {
            r.r1 = p.x;
            r.r2 = q.x;
            r.r3 = p.x;
        }

        @Actor
        public void actor2() {
            p.x = 3;
        }


    }
}
