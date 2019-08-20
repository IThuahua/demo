package com.example.demo.leetCode.string;

/**
 * 最长回文
 *
 * @Author zhoushenghua on
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String str = "1233321";
        System.out.println(longestPalindrome(str));
    }


    private static int maxLen = 0;
    private static String sub = "";
    public static String longestPalindrome(String s) {
        if(s.length() <= 1)
            return s;
        for(int i = 0;i < s.length()-1;i++){
            findLongestPalindrome(s,i,i);//单核回文
            findLongestPalindrome(s,i,i+1);//双核回文
        }
        return sub;
    }
    public static  void findLongestPalindrome(String s,int low,int high){
        while (low >= 0 && high <= s.length()-1){
            if(s.charAt(low) == s.charAt(high)){
                if(high - low + 1 > maxLen){
                    maxLen = high - low + 1;
                    sub = s.substring(low , high+1);
                }
                low --;//向两边扩散找当前字符为中心的最大回文子串
                high ++;
            }else
                break;
        }
    }
}
