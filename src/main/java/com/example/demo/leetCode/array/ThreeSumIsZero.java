package com.example.demo.leetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]

 * @Author zhoushenghua on
 */
public class ThreeSumIsZero {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum1(nums));
        

    }

    public static List<List<Integer>> threeSum1(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++){
            int left=i+1,right = nums.length-1;
            while(left < right && nums[i] <= 0 && nums[right] >= 0){
                if(nums[i]+nums[left]+nums[right] == 0){
                    if(!result.contains(Arrays.asList(nums[i], nums[left], nums[right]))){
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    }
                    while(left < right && nums[left] == nums[left+1]) left++;
                    while(left < right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }else if(nums[i]+nums[left]+nums[right] > 0){
                    while(left < right && nums[right] == nums[right-1]) right--;
                    right--;
                }else{
                    while(left < right && nums[left] == nums[left+1]) left++;
                    left++;
                }
            }
        }
        return result;

    }



    //超时 舍弃
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++){
            for(int j=i+1; j<nums.length-1; j++){
                for(int k=j+1; k<nums.length; k++){
                    if(nums[i]+nums[j]+nums[k] == 0){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if(!result.contains(list)){
                            result.add(list);
                        }
                    }
                }
            }
        }
        return result;


    }
}
