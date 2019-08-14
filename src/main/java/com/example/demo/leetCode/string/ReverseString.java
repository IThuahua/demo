package com.example.demo.leetCode.string;

import org.apache.commons.lang.StringUtils;

import java.util.Stack;

/**
 * 字符串倒序
 * @Author zhoushenghua on
 */
public class ReverseString {

    public static void main(String[] args) {
        String str = "123456";
        System.out.println("wayOne:" + wayOne(str));
        System.out.println("wayTwo:" + wayTwo(str));
        System.out.println("wayThree:" + wayThree(str));
        System.out.println("wayFour:" + wayFour(str));
        System.out.println("wayFive:" + wayFive(str));

    }

    //传统for循环遍历
    public static String wayOne(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        String result = "";
        for(int i=str.length()-1; i >= 0; i--){
            result += str.charAt(i);
        }
        return result;
    }

    //数组循环调换位置或数组的Collections的reverse方法,可参见数组倒置
    public static String wayTwo(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        char[] array = str.toCharArray();
        for(int i=0,len=array.length; i < len/2; i++){
            char temp = array[i];
            array[i] = array[len - 1 -i];
            array[len -1 - i] = temp;
        }
        return new String(array);
    }

    //StringBuilder的reverse
    public static String wayThree(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb.reverse().toString();
    }

    //栈的后进先出
    public static String wayFour(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        Stack<Character> stack = new Stack<>();
        char[] array = str.toCharArray();
        for (char c : array) {
            stack.push(c);
        }
        for(int i=0; i<str.length(); i++){
            array[i] = stack.pop();
        }
        return new String(array);
    }

    //迭代法
    public static String wayFive(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        if(str.length() == 1){
            return str;
        }else{
            return wayFive(str.substring(1)) + str.charAt(0);
        }

    }











}
