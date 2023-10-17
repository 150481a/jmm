package designpattern.principle;

import java.util.Scanner;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/6/6 20:18
 * @Version 1.0
 */
public class ChatDisplay {


    private static AbstractChat abstractChat;

    public static void main(String[] args) throws Exception {

        Scanner sc=new Scanner(System.in);
        String s = sc.nextLine();
        setAbstractChat(s);
        abstractChat.display();

    }

    private static  void setAbstractChat(String sc) throws Exception {
        abstractChat  = (AbstractChat)Class.forName(sc).newInstance();
    }
}
