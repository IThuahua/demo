package com.example.demo.leetCode.array;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @Author zhoushenghua on
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] arr = {1,1,-1,-1,3};
        int target = -1;
        System.out.println(threeSumClosest(arr, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int result = nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3];
        for (int i = 0; i < nums.length - 2; i++) {
            int left=i+1,right=nums.length-1;
            while (left < right){
                if(nums[i] + nums[left] + nums[right] == target){
                    return target;
                }
                if(Math.abs(nums[i] + nums[left] + nums[right] - target) <= Math.abs(result - target)){
                    result = nums[i] + nums[left] + nums[right];
                }
                if(nums[i] + nums[left] + nums[right] > target){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }

}
