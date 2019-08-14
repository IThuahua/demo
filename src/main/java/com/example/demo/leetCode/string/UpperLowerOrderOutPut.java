package com.example.demo.leetCode.string;

import org.apache.commons.lang.StringUtils;
import java.util.Arrays;

/**
 * 给定字符串，将字符串中的字符按大写字符，小写字符，数字，其他的顺序输出，如：acbABC+-/132*，输出：ABCabc123+-/*。
 * @Author zhoushenghua on
 */
public class UpperLowerOrderOutPut {

    public static void main(String[] args) {
        String str= "acbABC+-/132*";

        String string = way(str);
        System.out.println(string);

    }

    public static String way(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        //分离四种字符
        String upper = "", lower = "", num = "", other = "";
        for(int i=0; i<str.length(); i++){
            char temp = str.charAt(i);
            if(temp >= 'A' && temp <= 'Z'){
                upper += temp;
            }else if(temp >= 'a' && temp <= 'z'){
                lower += temp;
            }else if(temp >= '0' && temp <= '9'){
                num += temp;
            }else {
                other += temp;
            }
        }
        //对四种字符子串的字符进行升序排序
        if(StringUtils.isNotEmpty(upper)){
            upper = sortString(upper);
        }
        if(StringUtils.isNotEmpty(lower)){
            lower = sortString(lower);
        }
        if(StringUtils.isNotEmpty(num)){
            num = sortString(num);
        }
        return upper + lower + num + other;
    }

    public static String sortString(String str){
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }

    public static String sortNum(String str){
        int[] numArr = new int[str.length()];
        for(int i=0; i<numArr.length; i++){
            numArr[i] = Integer.valueOf(String.valueOf(str.charAt(i)));
        }
        Arrays.sort(numArr);
        String result = "";
        for(int i=0; i<numArr.length; i++){
            result += numArr[i];
        }
        return result;
    }

}
