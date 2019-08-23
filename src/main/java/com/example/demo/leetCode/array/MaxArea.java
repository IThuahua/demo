package com.example.demo.leetCode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。

 * @Author zhoushenghua on
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea1(arr));
        System.out.println(maxArea2(arr));

    }

    public static int maxArea1(int[] height){
        if(height.length < 2){
            return 0;
        }
        int result = 0;
        for(int i=0; i<height.length-1; i++){
            for(int j=i+1; j<height.length; j++){
                result = Math.min(height[i], height[j])*(j-i) > result?Math.min(height[i], height[j])*(j-i):result;
//                if(height[i] > height[j]){
//                    result = height[j] * (j-i) > result?height[j] * (j-i):result;
//                }else{
//                    result = height[i] * (j-i) > result?height[i] * (j-i):result;
//                }
            }
        }
        return result;
    }

    public static int maxArea2(int[] height){
        if(height.length < 2){
            return 0;
        }
        int result = 0;
        for(int i=0, j = height.length -1; i<j;){
            int temp = height[i]<height[j]?height[i++]:height[j--];
            result = Math.max(result,(j-i+1)*temp);
        }
        return result;


    }



}
