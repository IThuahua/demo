package com.example.demo.leetCode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 *
 * @Author zhoushenghua on
 */
public class FindSubString {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(findSubString(s, words));
    }

    public static List<Integer> findSubString(String s, String[] words){
        List<Integer> result = new ArrayList<>();
        int wordsLen = words.length; //数组元素个数
        if(wordsLen == 0){
            return result;
        }
        int wordsNum = words[0].length(); //数组元素的长度

        Map<String, Integer> wordsMap = new HashMap<>(); //value为相同key的个数
        for (int i = 0; i < words.length; i++) {
            wordsMap.put(words[i], wordsMap.getOrDefault(words[i], 0) + 1);
        }

        for(int i = 0; i < s.length() - wordsLen*wordsNum + 1; i++){
            Map<String, Integer> hasExists = new HashMap<>();
            int num = 0;
            while(num < wordsLen){
                String word = s.substring(i + num*wordsNum, i+(num + 1)*wordsNum);
                if(wordsMap.containsKey(word)){
                    hasExists.put(word, hasExists.getOrDefault(word, 0) + 1);
                    if(hasExists.get(word) > wordsMap.get(word)){
                        break;
                    }
                }else{
                    break;
                }
                num++;
            }
            if(num == wordsLen){
                result.add(i);
            }
        }
        return result;
    }

}
