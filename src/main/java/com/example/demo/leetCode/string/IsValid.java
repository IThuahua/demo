package com.example.demo.leetCode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * @Author zhoushenghua on
 */
public class IsValid {
    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
    }

    public static boolean isValid(String s){
        if(s == null){
            return false;
        }
        if(s == ""){
            return true;
        }
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');

        char[] arr = s.toCharArray();
        Stack stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            if(stack.size() != 0 && map.get(stack.peek()) != null && arr[i] == map.get(stack.peek())){
                stack.pop();
            }else{
                stack.push(arr[i]);
            }
        }
        return stack.size() == 0;
    }

}
