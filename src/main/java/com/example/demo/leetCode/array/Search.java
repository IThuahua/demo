package com.example.demo.leetCode.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 可以假设数组中不存在重复的元素。算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * @Author zhoushenghua on
 */
public class Search {
    public static void main(String[] args) {
        int[] arr = new int[]{4,5,6,0,1,2,3};
        int target = 2;
        System.out.println(search(arr, target));
    }

    public static int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] > nums[left]){
                if(target >= nums[left] && target < nums[mid]){
                    right = mid;
                }else {
                    left = mid;
                }
            }else if(nums[mid] < nums[right]){
                if(target <= nums[right] && target > nums[mid]){
                    left = mid;
                }else {
                    right = mid;
                }
            }
        }

        if(nums[left] == target){
            return left;
        }else if(nums[right] == target){
            return right;
        }
        return -1;
    }
}
