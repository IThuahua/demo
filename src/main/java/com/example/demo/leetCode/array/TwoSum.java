package com.example.demo.leetCode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但不能重复利用这个数组中同样的元素。
 * 如给定 nums = [2, 7, 11, 15], target = 9
 * 因nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * @Author zhoushenghua on
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        int[] result1 = twoSum(nums, target);
        System.out.println(Arrays.toString(result1));
        int[] result2 = twoSumByMap(nums, target);
        System.out.println(Arrays.toString(result2));
    }

    public static int[] twoSum(int[] nums, int target){
        int[] result = new int[2];
        for(int i=0; i < nums.length-1; i++){
            for(int j = i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    //用map,key:值,value:下标。用target减去已存原数组值为key，则获取到在获取到key值，则可与对应的k-v刚好组合
    public static int[] twoSumByMap(int[] nums, int target){
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for(int i= 0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                result[0] = i;
                result[1] = map.get(nums[i]);
                return result;
            }
            map.put(target - nums[i], i);
        }
        return result;
    }

}
