package com.example.demo.leetCode.array;

import java.util.Arrays;

/** 34
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1：输入: nums = [5,7,7,8,8,10], target = 8       输出: [3,4]
 * 示例 2：输入: nums = [5,7,7,8,8,10], target = 6       输出: [-1,-1]
 */
public class SearchIndex {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums.length == 0) {
            return result;
        }
        int first = 0,last = nums.length - 1;
        for(int i = 0; i < nums.length; i++,first++,last--){
            if ((result[0] != -1 && result[1] != -1)) {
                return result;
            }
            if (result[0] == -1 && nums[first] == target) {
                result[0] = first;
            }
            if (result[1] == -1 && nums[last] == target) {
                result[1] = last;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 6;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

}
