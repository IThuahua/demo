package com.example.demo.leetCode.string;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * @Author zhoushenghua on
 */
public class Zconvert {
    public static void main(String[] args) {
        String str = "LEETCODEISHIRING";
        int numRows = 3;
        System.out.println(convert(str, numRows));

    }

    public static String convert(String str, int numRows){
        if(str == null || numRows <= 1){
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int interval = (numRows - 1)*2;//第一行和最后一行两个数之间的间隔
        int step = 0; //除第一行和最后一行中间所有行的间隔
        for(int i= 0; i < numRows; i++){
            int index = i;
            step = i*2; //每一层都比上一层少2
            while (index < str.length()){
                sb.append(str.charAt(index));
                step = interval - step; //step步距来回交替
                index += (i == 0 || i == numRows -1)?interval:step;
            }
        }
        return sb.toString();


    }




}
