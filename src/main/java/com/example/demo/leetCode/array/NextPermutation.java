package com.example.demo.leetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @Author zhoushenghua on
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = {5,1,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums){
        if(nums.length <= 1){
            return;
        }
        int index = nums.length-1;
        while(nums[index] <= nums[index-1] && index >= 1){
            index--;
            if(index == 0){
                break;
            }
        }
        if(index == 0){
            //移到首元素了,则全列倒序排
            for(int i=0,len=nums.length; i < len/2; i++){
                int temp = nums[i];
                nums[i] = nums[len - 1 -i];
                nums[len -1 - i] = temp;
            }
        }else{
            //获取交换位置元素后所有元素中,最小的元素下标
            int secMaxIndex = index;
            for(int i = index; i < nums.length; i++){
                if(nums[secMaxIndex] > nums[i] && nums[i] > nums[index-1]){
                    secMaxIndex = i;
                }
            }
            int temp = nums[secMaxIndex];
            nums[secMaxIndex] = nums[index-1];
            nums[index-1] = temp;

            //后续所有数字正序排列
            int[] tempArr = new int[nums.length-index];
            System.arraycopy(nums, index, tempArr, 0, nums.length-index);
            Arrays.sort(tempArr);
            for(int i = index; i<nums.length; i++){
                nums[i] = tempArr[i-index];
            }
        }
    }

}
