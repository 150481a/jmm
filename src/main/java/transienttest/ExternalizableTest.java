package transienttest;

import java.io.*;

public class ExternalizableTest implements Externalizable {

    private   transient String  name="是的，我将会被序列化，不管我是否被transient关键字修饰";
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        name= (String) in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExternalizableTest test=new ExternalizableTest();

        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                new File("test")));
        out.writeObject(test);
        ObjectInput in = new ObjectInputStream(new FileInputStream(new File(
                "test")));
        test = (ExternalizableTest) in.readObject();
        System.out.println(test.name);

        out.close();
        in.close();
    }
}
