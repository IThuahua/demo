package com.example.demo.leetCode.array;

/**
 * q求两整数数最大公约数
 */
public class GreatestCommonDivisor {

    public static void main(String[] args) {

        System.out.println(greatestCommonDivisorOne(100, 80));
        System.out.println(greatestCommonDivisorTwo(100, 80));
        System.out.println(greatestCommonDivisorThree(100, 80));
        System.out.println(gcd(100, 80));

    }

    /**
     * 暴力求解
     * @param a
     * @param b
     * @return
     */
    public static int greatestCommonDivisorOne(int a, int b){
        int big = a > b ? a : b;
        int small = a > b ? b : a;
        if(big % small == 0){
            return b;
        }
        for (int i = small/2; i > 1 ; i--) {
            if(small % i == 0 && big % i == 0){
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法：a,b两者的最大公约数等于大数除以小数的余数和小数之间的最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int greatestCommonDivisorTwo(int a, int b){
        int big = a > b ? a : b;
        int small = a > b ? b : a;
        if(big % small == 0){
            return small;
        }
        return greatestCommonDivisorTwo(big%small, small);
    }


    /**
     * 更相减损术：a,b两者的最大公约数等于大数减小数的差值和小数之间的最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int greatestCommonDivisorThree(int a, int b){
        if(a == b){
            return a;
        }
        int big = a > b ? a : b;
        int samll = a > b ? b : a;
        return greatestCommonDivisorThree(big - samll, samll);
    }


    /**
     * 最优方案，辗转相除法与更相减损术的优势结合
     * a,b均为偶数时，gcd(a,b) = 2 * gcd(a/2, b/2) = 2 * gcd(a>>1, b>>1);
     * a为偶数，b为奇数时，gcd(a,b) = gcd(a/2, b) = gcd(a>>1，b);
     * a为奇数，b为偶数时，gcd(a,b) = gcd(a, b/2) = gcd(a, b>>1);
     * a,b均为奇数时，用更相减损术运算一次，gcd(a, b) = gcd(a-b, b),此时a-b必为偶数，可继续上面运算。
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b){
        if(a == b){
            return a;
        }
        if((a&1) == 0 && (b&1) == 0){
            return gcd(a>>1, b>>1);
        }else if((a&1) == 0 && (b&1) != 0){
            return gcd(a>>1, b);
        }else if((a&1) != 0 && (b&1) == 0){
            return gcd(a, b>>1);
        }else{
            int big = a > b ? a : b;
            int small = a > b ? b : a;
            return gcd(big - small, small);
        }



    }


}
