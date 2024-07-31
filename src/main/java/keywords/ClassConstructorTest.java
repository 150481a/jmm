package keywords;

public class ClassConstructorTest {

    public static void main(String[] args) {
        //super.getClass()和this.getClass()
        //getClass()是Object类定义的一个final方法，所有Java类的getClass()都继承自Object类。如前文所述，如果子类没有重写父类的某个成员方法，那么通过super来访问还是还是通过this来访问结果都是一样的。因此，super.getClass()和this.getClass()结果是一样的。Object类的getClass()方法返回的是该对象的运行时类，一个对象的运行时类是该对象通过new创建时指定的类。因此，super.getClass()和this.getClass()返回的都是new对象时指定的类。
        Childs child = new Childs();
        child.show();
    }
}


class  Parents{

    private Parents mSelt;

   Parents() {
        mSelt=this;
    }
    public void show(){
        System.out.println(this.getClass().getName());//Parent
        System.out.println(super.getClass().getName());//Parent
        System.out.println(mSelt.getClass().getName());//Parent
    }
}

class Childs extends Parents{
    public void show(){
        System.out.println(this.getClass().getName());//CHild
        System.out.println(super.getClass().getName());//Parent
        super.show();
    }

}
