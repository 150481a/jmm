package reflect;


import java.util.HashMap;

public class ReflectDemo  {






    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // 获取Class对象的三种方式
        System.out.println("根据类名:  \t"+User.class);
        System.out.println("根据对象:  \t"+new User().getClass());
        System.out.println("根据全限定类名:\t"+Class.forName("reflect.User"));


//         常用的方法

        Class<User> userClass = User.class;
        System.out.println("获取全限定类名:\t" + userClass.getName());
        System.out.println("获取类名:\t" + userClass.getSimpleName());
        System.out.println("实例化:\t" + userClass.newInstance());








    }





}

