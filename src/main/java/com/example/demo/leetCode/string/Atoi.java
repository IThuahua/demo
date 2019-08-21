package com.example.demo.leetCode.string;

/**
 * @Author zhoushenghua on
 */
public class Atoi {

    public static void main(String[] args) {
        String str = "2147483648";
        System.out.println(myAtoi(str));

    }

    public static int myAtoiSec(String str){
        str = str.trim();
        if(str == null || str.length() == 0){
            return 0;
        }
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;

        if(firstChar == '+'){
            sign = 1;
            start++;
        }else if(firstChar == '-'){
            sign = -1;
            start++;
        }
        for(int i=start; i<str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return (int)res * sign;
            }
            res = res * 10 + (str.charAt(i) - '0');
            if(sign == 1 && res > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            if(sign == -1 && res > Integer.MAX_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)res * sign;
    }




    public static int myAtoi(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        str = str.trim();
        StringBuilder sb = new StringBuilder();
        if(str.length() > 0 && (str.charAt(0) == '+' || str.charAt(0) == '-' || (str.charAt(0) >= '0' && str.charAt(0) <= '9'))){
            sb.append(str.charAt(0));
            for(int i=1; i<str.length(); i++){
                if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                    sb.append(str.charAt(i));
                }else{
                    break;
                }
            }
        }else{
            return 0;
        }
        String valStr = new String(sb);
        if(valStr.length() > 0 && (valStr.charAt(0) == '+' || valStr.charAt(0) == '-')){
            valStr = valStr.charAt(0) + valStr.substring(1,valStr.length()).replaceAll("^(0+)", "");
        }else{
            valStr = valStr.replaceAll("^(0+)", "");
        }
        if(valStr.length() == 0 || valStr.equals("+") || valStr.equals("-")){
            return 0;
        }else if(valStr.length() > String.valueOf(Integer.MIN_VALUE).length()){
            if(valStr.charAt(0) == '-'){
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }else{
            if(Long.valueOf(valStr) > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(Long.valueOf(valStr) < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }else{
                return Integer.valueOf(valStr);
            }
            //return (Long.valueOf(valStr) > Integer.MAX_VALUE || Long.valueOf(valStr) < Integer.MIN_VALUE) ? Integer.MIN_VALUE : Integer.valueOf(valStr) ;
        }
    }

}
