package designpattern.richters;

/**
 * @program: jmm
 * @description: 里氏代换原则 在软件系统中，一个可以接受基类(父类)对象的地方必然可以接受一个之类对象，里氏代换属于开闭原则的实现
 * @Author: xiang
 * @create: 2023/6/6 20:27
 * @Version 1.0
 */
public class Customer {

    public String sefInfo(){
        return  "给客户发送消息，客户名字："+getName()+",客户手机号码为："+getPhone();
    };

    private String name;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
