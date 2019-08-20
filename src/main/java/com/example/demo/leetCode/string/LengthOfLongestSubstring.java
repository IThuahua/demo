package com.example.demo.leetCode.string;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @Author zhoushenghua on
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("1231451"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("1123"));
    }

    public static int lengthOfLongestSubstring(String str){
        int len = 0;
        for(int i=0; i<str.length(); i++){
            String sb = "";
            sb += str.charAt(i);
            for(int j=i+1; j<str.length(); j++){
                char c = str.charAt(j);
                if(j == str.length() || sb.contains(c+"")){
                    break;
                }
                sb += c;
            }
            len = len > sb.length()?len:sb.length();
        }
        return len;

    }



}
