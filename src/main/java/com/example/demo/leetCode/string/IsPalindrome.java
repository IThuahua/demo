package com.example.demo.leetCode.string;

/**
 * 判断整数是否是回文
 * @Author zhoushenghua on
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));

        System.out.println(isPalindromeByString(10));
    }

    public static boolean isPalindrome(int x){
        if(x < 0){
            return false;
        }
        int temp = 0;
        int a = x;
        while(a > 0){
            temp = temp *10 + a%10;
            a /= 10;
        }
        return temp == x;
    }

    public static boolean isPalindromeByString(int x){
        String str = String.valueOf(x);
        if(str.length() % 2 == 1){
            for(int i=0; i<(str.length()- 1)/2; i++){
                if(str.charAt(i) != str.charAt(str.length()-1-i)){
                    return false;
                }
            }
        }else{
            for(int i=0; i<str.length()/2; i++){
                if(str.charAt(i) != str.charAt(str.length()-1-i)){
                    return false;
                }
            }
        }
        return true;
    }

}
