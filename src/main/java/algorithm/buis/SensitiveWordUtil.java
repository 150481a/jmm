package algorithm.buis;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: jmm
 * @description: DFA敏感词过滤算法
 * @Author: xiang
 * @create: 2023/7/28 14:38
 * @Version 1.0
 */
public class SensitiveWordUtil {

    public static final int SHORT_MATCH=1;


    public static final int LONG_MATCH=2;

    public static HashMap sensitiveWordMap;


    /**
     * 初始化敏感词
     * @param words 敏感词  以,分割
     */
    private static  void initSensitiveWordMap(String words){
        String[] w = words.split(",");
        sensitiveWordMap = new HashMap<>(w.length);
        Map nowMap;
        for (String key:
             w) {
            nowMap=sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换为char
                char keychar = key.charAt(i);
                //库中获取关键字
                Map  wordMap = (Map) nowMap.get(keychar);
                //如果不存在，新建一个，并加入词库
                if(null==wordMap){
                    wordMap= new HashMap<>();
                    wordMap.put("isEnd", "0");
                    nowMap.put(keychar,wordMap);
                }
                nowMap=wordMap;
                //是否为最后一个 并进行标记
                if(i==key.length()-1){
                    nowMap.put("isEnd", "1");
                }

            }

        }

    }

    /**
     *判断文字是否包含敏感词
     * @param txt  需要判断的文本
     * @param matchType 匹配长文本还是短文本
     * @return  若返回true 则包含敏感词
     */
    public static boolean contains(String txt,int matchType){
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag=checkSensitiveWord(txt,i,matchType);
            if(matchFlag>0){
                return  true;
            }
        }
        return false;
    }

    /**
     * 沿着文本字符串挨个往后检索文字中的敏感词
     * @param txt
     * @param matchType
     * @return
     */
    public  static Set<String> getSensitiveWord(String txt,int matchType){
        Set<String> sensitiveWordList = new HashSet<>();
        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感词
           int leght= checkSensitiveWord(txt,i,matchType);
           //存在加入 list中
           if(leght>0){
               sensitiveWordList.add(txt.substring(i,i+leght));

               //指针沿着文本往后移动敏感词长度
               //也就是一旦找到敏感词，加到列表后，越过这个敏感词，继续像后面匹配
               //这里的-1 是因为for循环会自动使i增加，如果不减会导致跳过部分关键词不参与检索

               i=i+leght-1;
           }
           //如果找不到，i就老实一个字一个字的往后移动，最为begin 继续下一轮
        }
        return sensitiveWordList;
    }

    /**
     * 从第beginIndex 开始向后查找敏感词
     * 如果找到 返回敏感词长度
     * 这个长度用于找到后提取敏感词和后移指针，是性能关注点
     * @param txt 待检查文本
     * @param beginIndex 检查位置
     * @param matchType 检查类型
     * @return
     */
    private static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        //敏感词结束标识位，用于敏感词只有一位的情况
        boolean flag=false;
        //匹配敏感词的个数，即敏感词的长度
        int length=0;
        char word;
        //从map中寻找
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            //被判断语句的第i个字符开始
            word= txt.charAt(i);
            //获取指定key，并且奖敏感词指针指向下一级map
            nowMap = (Map) nowMap.get(word);
            //存在 则判断是否为最后一个
            if(null!=nowMap){
                //找到相应的key,匹配长度+1

                length++;
                //如果作为最后一个匹配规则，结束循环，返回匹配标识数
                if("1".equals(nowMap.get("isEnd"))){
                    //结束标识为 true
                    flag=true;
                    //短匹配直接返回，长匹配继续寻找
                    if(SHORT_MATCH==matchType){
                        break;
                    }
                }
            }else break;//敏感词不存在 直接返回
        }
        if(length<2||!flag){
            //长度大于2 才算词，字的话就不需要折腾了
            length=0;
        }
        return length;
    }


    public static void main(String[] args) {
       //初始化敏感词库
        SensitiveWordUtil.initSensitiveWordMap("广告,中奖,广告词");
        System.out.println("敏感词结构："+ JSON.toJSONString(sensitiveWordMap));
        String str="关于中奖广告的广告词筛选";
        System.out.println("被检测文本："+str);
        System.out.println("被检测文本长度："+str.length());
        //是否命中短文本
        boolean contains = SensitiveWordUtil.contains(str, SensitiveWordUtil.SHORT_MATCH);
        System.out.println("短匹配："+contains);
        //是否命中短文本
        boolean contains1 = SensitiveWordUtil.contains(str, SensitiveWordUtil.LONG_MATCH);
        System.out.println("长匹配："+contains1);

        Set<String> sensitiveWord = SensitiveWordUtil.getSensitiveWord(str, SensitiveWordUtil.SHORT_MATCH);

        System.out.println("短匹配："+sensitiveWord);

        Set<String> sensitiveWord1 = SensitiveWordUtil.getSensitiveWord(str, SensitiveWordUtil.LONG_MATCH);

        System.out.println("长匹配："+sensitiveWord1);

    }

}
