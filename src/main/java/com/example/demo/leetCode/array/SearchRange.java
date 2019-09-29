package com.example.demo.leetCode.array;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @Author zhoushenghua on
 */
public class SearchRange {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2};
        int target = 2;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    public static int[] searchRange(int[] nums, int target){
        if(nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target){
            return new int[]{-1,-1};
        }
        if(nums.length == 1 && nums[0] == target){
            return new int[]{0,0};
        }

        int left = 0, right = nums.length - 1;

        while (left + 1 <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                int first = mid;
                int second = mid;
                while (first - 1 >= 0 && nums[first - 1] == target){
                    first = first - 1;
                }
                while (second + 1 <= nums.length-1 && nums[second + 1] == target){
                    second = second + 1;
                }
                return new int[]{first, second};
            }else if(nums[mid] > target){
                right = mid;
            }else{
                left = mid;
            }
        }
        return new int[]{-1,-1};
    }

}
