package com.example.demo.leetCode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * @Author zhoushenghua on
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        //注意:给定数组是有序数组
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates2(nums));

        int[] nums1 = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(RemoveElement.removeElement(nums1,2));
        for (int i = 0; i < 8; i++) {
            System.out.print(nums1[i]);
        }
    }

    public static int removeDuplicates2(int[] nums){
        if(nums == null || nums.length == 1){
            return nums.length;
        }
        int i=0,j=1;
        while (j < nums.length){
            if(nums[i] == nums[j]){
                j++;
            }else{
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i+1;
    }

    //无序数组可用次方法,时间复杂度为O(n)
    public static int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 1){
            return nums.length;
        }
        List<Integer> hasExists = new ArrayList<>();
        int exchangeTimes = 0;
        for (int i = 0; i < nums.length;) {
            if((exchangeTimes+i) >= nums.length){
                break;
            }
            if(hasExists.contains(nums[i])){
                int temp = nums[i];
                nums[i] = nums[nums.length-1-exchangeTimes];
                nums[nums.length-1] = temp;
                exchangeTimes++;
            }else{
                hasExists.add(nums[i]);
                i++;
            }
        }
        return nums.length - exchangeTimes;
    }
}

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 示例 2:
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。
 */
class RemoveElement{

    public static int removeElement(int[] nums, int val){
        if(nums == null){
            return 0;
        }
        int i=0,j=0;
        while (j < nums.length){
            if(nums[j] != val){
                i++;
                j++;
            }else{
                while (j<nums.length){
                    if(nums[j] == val){
                        j++;
                    }else{
                        //交换
//                        int temp = nums[i];
//                        nums[i] = nums[j];
//                        nums[j] = temp;
                        nums[i] = nums[i] + nums[j];
                        nums[j] = nums[i] - nums[j];
                        nums[i] = nums[i] - nums[j];
                        i++;
                        j=i;
                        break;
                    }
                }
            }
        }
        return i;
    }

}
