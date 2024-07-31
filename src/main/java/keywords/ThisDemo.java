package keywords;

/**
 *
 */
public class ThisDemo {

    public static void main(String[] args) {
        //main()函数中有两条语句，new Child()和child.show()。
        //第一条语句new Child()时要执行Child类的构造方法，但是Child类是Parent类的子类，因此会先执行Parent类的构造方法。Child类的无参构造函数中没有使用super和this来调用父类或本类中的其他的构造方法，因此会调用父类的无参构造函数。在父类的无参构造函数Parent()中调用了执行了this(1)，此调用表示执行父类中有一个整数参数的构造方法，虽然子类中也有一个有一个整数参数的构造方法，但是并不会被执行。父类中有一个整数参数的构造方法执行this.str=”Parent”，这里的this.str代表的是父类中的成员变量str，虽然子类中也有一个成员变量str，但是并不会被赋值。将父类中的成员变量str赋值为”Parent”后，接着执行了this.show()，虽然父类中有一个show()方法，但由于子类重写了show()方法，所以this.show()执行的子类的show()方法。子类的show()方法首先执行了打印str的操作，此时打印的显然是子类中的str，子类的str没有被赋值，因为打印null。接着子类的show()方法执行了super.show()，即调用了父类的show()方法，在父类的show()方法中执行了打印this.str的操作，this.str同样代表父类中的成员变量str，因此打印”Parent”。
        //第二条语句child.show()先是执行子类的show()方法，子类的show()先是打印了子类的str值（null），然后执行了父类的show()打印了父类的str值（”Parent”）。
        //两条语句的打印结果为null, Parent, null, Parent。
        //如果将第一条语句改为new Child(1)，则执行的是子类的有一个整数参数的构造方法，仍然是先执行父类的无参构造方法，初始化父类的str为”Parent”，然后执行子类的show()，子类的show()打印子类的str值（null），然后执行父类的show()，父类show()打印父类的str值（”Parent”），然后执行子类的构造方法将子类的str初始化为”Child”。 第二条语句child.show()先是执行子类的show()方法，子类的show()先是打印了子类的str值（”Child”），然后执行了父类的show()打印了父类的str值（”Parent”）。
        //两条语句的打印结果为null, Parent, Child, Parent。
        Child child=new Child();
        child.show();
        //此时输出 应该是   null  Parent  Parent
    }
}


/**
 * 1）只能在构造方法中通过this来调用其他构造方法，普通方法中不能使用。
 * 2) 不能通过this递归调用构造方法，即不能在一个构造方法中通过this直接或间接调用该构造方法本身。
 * 3) 通过this调用其他构造方法必须放在构造方法的第一行中执行。由于super调用父类的构造函数也必须放在构造方法的第一行中执行，因此，通过this和super调用构造方法不能同时出现一个构造方法中。也不能在一个构造方法中多次调用不同的构造方法。
 * 在构造方法中也可以使用this关键字来访问本类中的成员变量和成员函数。其用法和非构造方法中的this关键字相同。

 */
//class  Testdg{
//
//    public void test(){
//        this();
//    }
//
//    public Testdg(){
//        this(1);
//    }
//    public Testdg(int i){
//        this();
//    }
//    public Testdg(int i ,int j){
//        this(i,j);
//    }
//}
//
//class T extends Testdg{
//
//    public T(){
//        super();
//        this();
//
//    }
//}


class Parent{
    public  String str;

    public Parent(){
        this(1);
    }
    public Parent(int i){
        this.str="Parent";
        this.show();
    }

    public void show() {
        System.out.println(this.str);
    }

}

class Child extends Parent{
    public String str;

    public Child(){
       super();
    }

    public Child(int i){
        str="Child";

    }
    public void show(){
        System.out.println("当前类属性："+this.str);
        System.out.println("父类扥属性"+super.str);
        super.show();
    }
}