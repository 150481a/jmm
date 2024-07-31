package algorithm.stack;


import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 使用栈 来解决匹配括号是否匹配问题
 */
public class BracketDemo {


    //数组栈
    public  ArrayStack arrayStack=new ArrayStack();

    public HashMap<String,String> keys;

    public BracketDemo(){
        keys=new HashMap<>();
        keys.put("{","}");
        keys.put("[","]");
        keys.put("(",")");
    }


    public static void main(String[] args) {
        BracketDemo b=new BracketDemo();

        //残缺符号组
        String str0="{}()[]";

        //残缺符号组
        String str="{[({(({{([[{])}}))})]}";



        //完美符号组
        String str1="{[([({{([([{((([[]])))}])])}})])]}";

        //异常符号组
        String str2="{[({(({’，！！{([[{、32433234}])}})‘，)})]}";

//        String[] strs0 =b.clean( b.splitSizes(str0));
//        System.out.println("数组长度1为："+strs0.length+";数组内容为："+ Arrays.toString(strs0)+"是否是完美匹配："+b.checkComplete(strs0));


        String[] strs =b.clean( b.splitSizes(str));
        System.out.println("数组长度1为："+strs.length+";数组内容为："+ Arrays.toString(strs)+"是否是完美匹配："+b.checkComplete(strs));


        String[] strs1 = b.clean(b.splitSizes(str1));
        System.out.println("数组长度2为："+strs1.length+";数组内容为："+ Arrays.toString(strs1)+"是否是完美匹配："+b.checkComplete(strs1));

        String[] strs2 = b.clean(b.splitSizes(str1));
        System.out.println("数组长度2为："+strs2.length+";数组内容为："+ Arrays.toString(strs2)+"是否是完美匹配："+b.checkComplete(strs2));



    }


    /**
     * 将字符串分割为数组  寻常 for循环切割
     * @param s
     * @return
     */
    public   String[] splitSizes(String s){

        if(null==s||"".equals(s)){
            return null;
        }
        String[] strs=new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            strs[i]=s.substring(i, i + 1);
        }
        return  strs;
    }

    public String[] clean(String[] strs){
        ArrayList<String> list=new ArrayList<>();
        Iterator<String> iterator = Arrays.stream(strs).iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            String stt="[]{}()";
            if(!stt.contains(next)){
                iterator.remove();
            }
            list.add(next);
        }
        return list.stream().toArray(e->new String[e]) ;
    }

    public static <T> Stream<T>
    getStreamFromIterator(Iterator<T> iterator)
    {

        // Convert the iterator to Spliterator
        Spliterator<T>
                spliterator = Spliterators
                .spliteratorUnknownSize(iterator, 0);

        // Get a Sequential Stream from spliterator
        return StreamSupport.stream(spliterator, false);
    }


    public boolean  checkComplete(String[] str){
        if(null==str||str.length==0)
            return false;

        arrayStack.clear();
        int  i=0;
        int k=0;
        int num=0;
        for (String s:
             str) {
            if(i!=0){
                String peek = arrayStack.peek();
                if(!isEmpty(peek)){
                    String s1 = keys.get(s);
                    if(isEmpty(s1)){
                        num=arrayStack.size();
                       break;
                    }else{
                        arrayStack.push(s);
                    }
                }
            }else{
                arrayStack.push(s);
            }
            i++;

        }
        for (String s:
                str) {
            String s1 = keys.get(s);
            if(isEmpty(s1)){
                String pop = arrayStack.pop();
                String s2 = keys.get(pop);
                if(s.equals(s2)){
                    k++;
                }
            }

        }
        if(k==num)
            return true;
        return false;

    }


    public  boolean  isEmpty(String str){
        return null==str||"".equals(str);
    }
}

/**
 * 自定义集合作为栈
 */
class ArrayStack{

    private ArrayList<String> stack;
    public ArrayStack(){
        stack=new ArrayList<>();
    }

    /**
     *  返回栈数量
     * @return
     */
    public int size(){
        return stack.size();
    }


    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return  stack.size()==0;
    }


    /**
     * 入栈
     */
    public void push(String str){
        stack.add(str);
    }

    /**
     * 出栈
     * @return
     */
    public  String pop(){
        if(isEmpty())
            throw  new IndexOutOfBoundsException();
        return stack.remove(size()-1);
    }

    /**
     * 访问顶层元素
     * @return
     */
    public String peek(){
        if(isEmpty())
            throw new IndexOutOfBoundsException();
        return stack.get(size()-1);
    }


    public Object[]toArray(){
        return stack.toArray();
    }

    /**
     * 清空栈
     */
    public void clear(){
        stack.clear();
    }

}
