package designpattern.richters;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/6 20:39
 * @Version 1.0
 */
public class SendMessage {

    public static void main(String[] args) {
      Customer commonCustomer=new   CommonCustomer();
      commonCustomer.setName("张沙河");
      commonCustomer.setPhone("123232");

      Customer vipCustomer=new VipCustomer();
        vipCustomer.setName("张沙河1");
        vipCustomer.setPhone("12323122");
      //普通用户发送消息
      send(commonCustomer);

       //VIP用户发送消息
       send(vipCustomer);

    }

   public static void send(Customer customer){
        System.out.println(customer.sefInfo());
    }
}
