package com.example.demo.leetCode.array;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 取数组中只出现一次的数
 * @Author zhoushenghua on
 */
public class AppearOnce {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,3,3,3,3,4,4,5};
        System.out.println(getNumAppearOnce(nums));
    }

    public static Set<Integer> getNumAppearOnce(int[] nums){
        //保存只存在一次的元素
        Set<Integer> set = new HashSet<>();
        //保存重复一次或多次的元素
        Set<Integer> repeatEle = new HashSet<>();
        for(int i=0; i< nums.length; i++){
            if(!set.add(nums[i])){
                repeatEle.add(nums[i]);
            }
        }
        if(!CollectionUtils.isEmpty(repeatEle)){
            for (Integer integer : repeatEle) {
                set.remove(integer);
            }
        }
        return set;
    }
}
