package reflect;

public class TestDemos {
    static class Cile{
         int c;
         Cile(int i){
             c = i;
         }

         public int getC() {
             return c;
         }

         public void setC(int c) {
             this.c = c;
         }
     }


     public static void change(int k){
         new Cile(k);
     }

    public static void change2(int k){

    }

    public static void main(String[] args) {
        Cile cile=new Cile(1);
        change(20);
        System.out.println("c:"+cile.c);


    }
}
