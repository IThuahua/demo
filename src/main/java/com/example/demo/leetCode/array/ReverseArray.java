package com.example.demo.leetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @Author zhoushenghua on
 */
public class ReverseArray {
    public static void main(String[] args) {
        int[] nums = {1,2,4,5,3,6};
        wayOne(nums);
        System.out.println(Arrays.toString(nums));

        //第二种方法
        List<Integer> list = new ArrayList<>();
        for(int i=nums.length - 1; i >= 0; i--){
            list.add(nums[i]);
        }
        Object[] arr = list.toArray();
        System.out.println(Arrays.toString(arr));

        //第三种方法
        Collections.reverse(Arrays.asList(nums));
        System.out.println(Arrays.toString(nums));

    }

    public static void wayOne(int[] nums){
        for(int i=0,len=nums.length; i < len/2; i++){
            int temp = nums[i];
            nums[i] = nums[len - 1 -i];
            nums[len -1 - i] = temp;
        }
    }












}
