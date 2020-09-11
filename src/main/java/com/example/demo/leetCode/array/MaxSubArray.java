package com.example.demo.leetCode.array;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray {

    /**
     * 贪心算法，若某个值前的n个数的和小于0，则不进行累加，否则进行累加。
     * @param nums
     * @return
     */
    public static int max(int[] nums){
        if (nums == null) {
            return 0;
        }
        int preSum = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            if (preSum < 0) {
                preSum = temp;
            } else {
                preSum += temp;
            }
            result = Math.max(result, preSum);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(max(nums));

    }

}
