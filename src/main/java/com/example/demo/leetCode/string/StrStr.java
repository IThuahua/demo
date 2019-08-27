package com.example.demo.leetCode.string;

/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * @Author zhoushenghua on
 */
public class StrStr {
    public static void main(String[] args) {
        String haystack = "aaa";
        String needle = "aaaa";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle){
        if(needle == null || needle.length() == 0){
            return 0;
        }
        if(haystack == null || haystack.length() == 0){
            return -1;
        }
        char[] arr = haystack.toCharArray();
        char[] needleArr = needle.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(needleArr[0] == arr[i]){
                boolean flag = true;
                for (int j = 1; j < needleArr.length; j++) {
                    if(arr.length-i < needleArr.length || needleArr[j] != arr[i+j]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    return i;
                }
            }
        }
        return -1;

    }
}
