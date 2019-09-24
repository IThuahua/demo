package com.example.demo.leetCode.string;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode/
 * @Author zhoushenghua on
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        String s = ")(((()))";
        System.out.println(longestValidParentheses1(s));
        System.out.println(longestValidParentheses2(s));
        System.out.println(longestValidParentheses3(s));
    }

    //用stack暴力求解
    public static int longestValidParentheses1(String s){
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i+2; j <= s.length(); j+=2){
                if(isValid(s.substring(i, j))){
                    result = Math.max(result, j-i);
                }
            }
        }
        return result;
    }
    private static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push('(');
            }else if(!stack.empty() && stack.peek() == '('){
                stack.pop();
            }else {
                return false;
            }
        }
        return stack.empty();
    }

    //动态规划
    public static int longestValidParentheses2(String s){
        int result = 0;
        int temp[] = new int[s.length()];
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                if(s.charAt(i - 1) == '('){
                    temp[i] = (i >= 2 ? temp[i - 2] : 0) + 2;
                }else if(i - temp[i - 1] > 0 && s.charAt(i - temp[i - 1] - 1 ) == '('){
                    temp[i] = temp[i - 1] + ((i - temp[i - 1]) >= 2 ? temp[i - temp[i - 1] - 2] : 0) + 2;
                }
                result = Math.max(result, temp[i]);
            }
        }
        return result;
    }

    //用stack
    public static int longestValidParentheses3(String s){
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.empty()){
                    stack.push(i);
                }else{
                    result = Math.max(result, i - stack.peek());
                }
            }
        }
        return result;
    }


}
