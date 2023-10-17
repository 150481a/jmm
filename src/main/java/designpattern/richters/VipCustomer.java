package designpattern.richters;


/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/6 20:31
 * @Version 1.0
 */
public class VipCustomer extends Customer {
   @Override
    public String sefInfo(){
        return "给VIP客户发送消息，客户名字："+super.getName()+",客户手机号码为："+super.getPhone();
    };

}
