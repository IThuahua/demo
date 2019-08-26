package com.example.demo.leetCode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * @Author zhoushenghua on
 */
public class LetterCombinations {
    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits){
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0){
            return result;
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        doGet("", 0, result, digits, map);
        return result;
    }

    private static void doGet(String s, int index, List<String> result, String digits, Map<Character, String> map){
        if(index == digits.length()){
            result.add(s);
            return;
        }
        String letter = map.get(digits.charAt(index));
        for(int i=0; i<letter.length(); i++){
            doGet(s+letter.charAt(i), index+1, result, digits, map);
        }
    }

}
