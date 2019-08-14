package com.example.demo.leetCode.array;

import java.util.Arrays;

/**
 * 采用快排思想,时间复杂度为O(n)
 * @Author zhoushenghua on
 */
public class OddNumInHead {
    public static void main(String[] args) {
        int[] nums = {1,2,4,5,3};
        oddNumInHead(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void oddNumInHead(int[] nums){
        int len = nums.length;
        if(len <= 0) return;
        int head = 0, tail = len - 1;
        while(head < tail){
            while (head < len && nums[head]%2 == 1) head++;
            while (tail >= 0 && nums[tail]%2 == 0) tail--;
            if(head < tail){
                int temp = nums[head];
                nums[head] = nums[tail];
                nums[tail] = temp;
            }
        }
    }

}
