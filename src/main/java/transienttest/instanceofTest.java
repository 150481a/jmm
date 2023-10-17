package transienttest;

import java.io.*;
import java.util.Scanner;

public class instanceofTest extends  A {




    public static void main(String[] args) {


        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("E:/user.txt")));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("E:/out.txt")))


        ) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }




    }

}

class A{
  A(){
     System.out.println("A");
  }

}

