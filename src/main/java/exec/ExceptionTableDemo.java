package exec;

//查看异常表
//javac ExceptionTableDemo  ->  javap -c ExceptionTableDemo
// public static void simpleTryCatch();
//    Code:
//       0: invokestatic  #3                  // Method testNPE:()V
//       3: goto          11
//       6: astore_0
//       7: aload_0
//       8: invokevirtual #5                  // Method java/lang/Exception.printStackTrace:()V
//      11: return
//    Exception table:
//       from    to  target type
//           0     3     6   Class java/lang/Exception
//from 可能发生异常的起始点
//to 可能发生异常的结束点
//target 上述from和to之前发生异常后的异常处理者的位置
//type 异常处理者处理的异常的类信息
public class ExceptionTableDemo {

    public static void simpleTryCatch() {
        try {
            testNPE();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testNPE() {

    }


}
