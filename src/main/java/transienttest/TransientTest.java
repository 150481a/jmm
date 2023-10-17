package transienttest;

import java.io.*;

public class TransientTest {

    public static void main(String[] args) {

        User user = new User();
        user.setId(1);
        user.setName("测试序列化 中  实现 Serializable 接口  后使用transient 关键字后是否被序列化 ");
        user.setPasword("能看见我就是被序列化咯");
        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getName());
        System.err.println("password: " + user.getPasword());

        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("E:/user.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            User.name="zhans";
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "E:/user.txt"));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();

            System.out.println("\nread after Serializable: ");
            System.out.println("username: " + user.getName());
            System.err.println("password: " + user.getPasword());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
