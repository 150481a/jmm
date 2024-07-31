package keywords;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 针对super  this关键字用法 ：
 * super  当之类需要调用父类的方法是，需要使用super关键字来进行获取父类的属性字段方法
 * 一是用在子类的构造方法里面；二是用在子类里调用隐藏或重写的属性或行为；
 * 1.super关键字可以在子类的构造方法中显式地调用父类的构造方法，super（）必须为子类构造函数的第一行；
 * 2.super可以用来访问父类的成员变量或者方法，当子类成员变量或方法与父类有相同的名字时也会覆盖父类的成员变量或方法，这个时候想要访问父类的成员变量或方法只能通过super关键字来访问，子类方法中的super.方法名（）不需要位于第一行。
 */
public class SuperDemo {

    public static void main(String[] args) {
        Saner saner=new Saner();
        saner.f();
    }

    // super  this 区别
    //1、 在构造方法中 super  this两个关键字不能同时存在，this和super不能同时存在一个构造器中
    //2、 super适用于 之类覆盖了父类的属性方法，调用父类的属性方法，而 this  则是在子类没有重写父类属性方法，在子类中this和super访问成员变量都是一个效果
    //3、this和super都指对象，不能再static 方法类中使用
    //4、this本质指向对象的指针，super是一个关键字
    //5、this可以调用构造器，不能调用两个， 可能会存在递归调用构造器情况
    //6、this是指对象的引用，而super是子类与父类之间的桥梁



    //arratlist  linkedlist区别
    //  1、底层实现不一样，arraylist是基于数组实现，而linkedlist是基于链表实现的 ，
    //  2、各自注重的效率不一样，arraylist的效率注重查找，linkedlist则是添加删除。
    //  3、arraylist支持随机访问，而linkedlist不能。支持顺序访问，
    //  4、
    //



}


class Fiather{
    int value=100;

    public void f(){
        ArrayList<Integer> k=new ArrayList<>();
        LinkedList<Integer> s=new LinkedList<>();
        System.out.println("父类关键词的值 value="+value);
    }

  public Fiather(){
        System.out.println("Fiather constructor");
  }

}

class Saner extends Fiather{
    public Saner() {
        super();
        System.out.println("Saner constructor");
    }

    int value;



    @Override
    public void f() {
        value=200;

        super.f();

        System.out.println("子类的value属性值="+value);  //子类的value属性值=200
        //打印出来的是子类自定义的那个value的值，这个值是200
        System.out.println(value);  //200
        /**
         * 打印出来的是父类里面的value值，由于子类在重写从父类继承下来的f()方法时，
         * 第一句话“super.f();”是让父类对象的引用对象调用父类对象的f()方法，
         * 即相当于是这个父类对象自己调用f()方法去改变自己的value属性的值，由0变了100。
         * 所以这里打印出来的value值是100。
         */
        System.out.println(super.value);  //100
        //this关键字指向当前的对象
        System.out.println(this.value);

    }
}
