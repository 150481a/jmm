package algorithm.buis;

import java.util.Arrays;

/**
 * @program: jmm
 * @description: TODO
 * @Author: xiang
 * @create: 2023/7/28 16:07
 * @Version 1.0
 */
public class Topk {

    public static void down(int [] node,int i){
        while (i<<1 < node.length){
            int left = i << 1;
            int right = left + 1;
            int flag=i;
            if(node[i]>node[left]){
                flag=left;
            }
            if(right< node.length&&node[flag]>node[right]){
                flag=right;
            }
            if(flag!=i){
                int temp = node[i];
                node[i]=node[flag];
                node[flag]=temp;
                i=flag;
            }else  break;

        }

    }

    public static void main(String[] args) {
        int [] src={2,3,4,5,6,7,1,9,10,0};
        int k=5;
        int[] nodes=new int[k+1];

        for (int i = 0; i <k; i++) {
            nodes[i+1]=src[i];
        }
        System.out.println("before:"+ Arrays.toString(nodes));

        for (int i=k>>1;i>=1;i--){
            down(nodes,i);
        }
        System.out.println("create:"+ Arrays.toString(nodes));
        for (int i = src.length-k; i < src.length; i++) {
            if(nodes[1]<src[i]){
                nodes[1]=src[i];
                down(nodes,1);
            }
        }
        System.out.println("tops:"+ Arrays.toString(nodes));

    }
}
