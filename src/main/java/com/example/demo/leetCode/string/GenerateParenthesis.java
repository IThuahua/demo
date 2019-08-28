package com.example.demo.leetCode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *

 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    static List<String> result = new ArrayList<>();
    public static List<String> generateParenthesis(int n){
        char[] arr = new char[2*n];
        generate(0, n, n, arr);
        return result;
    }

    public static void generate(int index, int left, int right, char[] arr){
        if(left == 0 && right == 0){
            result.add(new String(arr));
            return;
        }

        if(left-1 >= 0){
            arr[index] = '(';
            generate(index+1, left-1, right, arr);
        }

        if(right-1 >= left){
            arr[index] = ')';
            generate(index+1, left, right-1, arr);
        }
    }

}
