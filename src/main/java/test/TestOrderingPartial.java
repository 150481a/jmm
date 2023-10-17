package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/5/22 13:58
 * @Version 1.0
 */
public class TestOrderingPartial {

    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "1, 0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case1 {
        int x, y;

        @Actor
        public void acror1() {
            x = 1;
            y = 1;
        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            r.r2 = x;
        }

    }

    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "1, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case2 {
        int x;
        volatile int y;

        @Actor
        public void acror1() {
            x = 1;
            y = 1;

        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            r.r2 = x;
        }

    }

    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "1, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case3 {
        volatile int x;
        int y;

        @Actor
        public void acror1() {
            y = 1;
            x = 1;
        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            r.r2 = x;
        }

    }
}
