package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * @program: jmm
 * @description: volatile 写 要用来收官(写在最后)，volatile 读要用来开篇(放在最前面)
 *  凡是需要cas操作的地方 都需要加 volatile  ，
 *  volatile 只能有序性+可见性   无原子性
 *  cas +volatile 有原子性
 *  cas（compare and swap） 是一种用于在多线程环境下实现同步功能的机制，包含三个操作数-- 内存位置、预期数值和新值， 比较并交换
 * @Author: xiang
 * @create: 2023/5/22 15:08
 * @Version 1.0
 */
public class TestOrderingTotal {
    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "0, 0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case1 {
        int x,y;

        @Actor
        public void acror1(II_Result r) {
            y = 1;
           r.r2 = x;

        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            x = 1;
        }

    }

    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "0, 0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case2 {
        int x;
        volatile  int y;

        @Actor
        public void acror1(II_Result r) {
            y = 1;
            r.r2 = x;

        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            x = 1;
        }

    }

    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "0, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case3 {
        int x;
        volatile  int y;

        @Actor
        public void acror1(II_Result r) {
            y = 1;
            r.r2 = x;

        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            x = 1;
        }

    }

    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "0, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case4 {
        volatile int x;
        volatile  int y;

        @Actor
        public void acror1(II_Result r) {
            y = 1;
            r.r2 = x;

        }

        @Actor
        public void acror2(II_Result r) {
            r.r1 = y;
            x = 1;
        }

    }


}
