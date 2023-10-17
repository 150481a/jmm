package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/5/4 1:38
 * @Version 1.0
 */

@JCStressTest
@State
@Outcome(id="1, 0",expect = Expect.ACCEPTABLE)
@Outcome(id="0, 2",expect = Expect.ACCEPTABLE)
@Outcome(id="1, 2",expect = Expect.ACCEPTABLE)
@Outcome(id="0, 0",expect = Expect.ACCEPTABLE_INTERESTING)
public class TestPossible {

    int a,b;


    @Actor
    public void action1(II_Result r){
        a=1;
        r.r2=b;
    }

    @Actor
    public void action2(II_Result r){
        b=2;
        r.r1=a;
    }


}
