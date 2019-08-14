package com.example.demo.leetCode.string;

import java.util.HashSet;

/**
 * @Author zhoushenghua on
 */
public class S1S2makeUpS3 {

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "12345";
        String s3 = "abcde12345";
        boolean flag = isInterleave2(s1,s2,s3);
        System.out.println(flag);
    }

    public static boolean isInterleave1(String s1, String s2, String s3){
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if(len1 + len2 != len3){
            return false;
        }
        boolean val[][] = new boolean[len1+1][len1+1];
        val[0][0] = true;
        for(int i=1; i <= len1; i++){
            val[i][0] = val[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }
        for(int i=1; i <= len2; i++){
            val[0][i] = val[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
        }
        for(int i=1;i <= len1; i++){
            for(int j=1; j<= len2; j++){
                val[i][j] = (val[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)) ||
                        (val[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1));
            }
        }
        return val[len1][len2];
    }

    public static boolean isInterleave2(String s1, String s2, String s3){
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        HashSet<Integer> cache = new HashSet<>();
        return isInterleave(s1,s2,s3,0,0,cache);
    }

    public static boolean isInterleave(String s1, String s2, String s3,int p1, int p2, HashSet<Integer> cache){
        if(p1 + p2 == s3.length()){
            return true;
        }
        if(cache.contains(p1*s3.length() + p2)){
            return false;
        }
        cache.add(p1 * s3.length() + p2);
        boolean match1 = p1 < s1.length() && s3.charAt(p1 + p2) == s1.charAt(p1);
        boolean match2 = p2 < s2.length() && s3.charAt(p1 + p2) == s2.charAt(p2);
        if(match1 && match2){
            return isInterleave(s1, s2, s3, p1+1, p2, cache) || isInterleave(s1, s2, s3, p1, p2+1, cache);
        }else if(match1){
            return isInterleave(s1, s2, s3, p1+1, p2, cache);
        }else if(match2){
            return isInterleave(s1, s2, s3, p1, p2+1, cache);
        }else{
            return false;
        }
    }

}
