package com.example.demo.leetCode.array;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 *
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 * @Author zhoushenghua on
 */
public class Divide {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648, 1));
        System.out.println(divide(7, -3));
    }

    public static int divide(int dividend, int divisor){
        if(divisor == 0){
            throw new IllegalArgumentException("被除数不能为0");
        }
        if(dividend == 0){
            return 0;
        }
        if(dividend == Integer.MIN_VALUE && divisor == -1){ //只有此情况会溢出
            return Integer.MAX_VALUE;
        }
        boolean flag = (dividend ^ divisor) >= 0 ;
        long dividendAbs = Math.abs((long)dividend);
        long divisorAbs = Math.abs((long)divisor);
        int result = 0;
        for(int i=31; i >=0; i--){
            if((dividendAbs >> i) >= divisorAbs){
                result += (1<<i);
                dividendAbs -= (divisorAbs << i);
            }
        }
        return flag?result:-result;



//        超时
//        long dividedAbs = dividend < 0 ? -(long)dividend : dividend;
//        long divisorAbs = divisor < 0 ? -(long)divisor : divisor;
//        int result = 0;
//        while (true){
//            if(dividedAbs < divisorAbs){
//                return flag?result:-result;
//            }else{
//                dividedAbs -= divisorAbs;
//                result++;
//            }
//        }


    }
}
