package com.example.demo.leetCode.string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 说明:所有输入只包含小写字母 a-z 。
 *
 * @Author zhoushenghua on
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));

        String[] strs1 = {"aa","a"};
        System.out.println(longestCommonPrefix(strs1));
    }

    public static String longestCommonPrefix(String[] strs){
        if(strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }

        StringBuilder sb = new StringBuilder();
        String first = strs[0];
        for(int i=0; i<first.length(); i++){
            for(int j=1; j<strs.length; j++){
                if(strs[j].length() < i+1 || first.charAt(i) != strs[j].charAt(i)){
                    return sb.toString();
                }
            }
            sb.append(first.charAt(i));
        }
        return sb.toString();
    }



}
