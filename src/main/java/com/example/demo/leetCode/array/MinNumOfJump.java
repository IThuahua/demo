package com.example.demo.leetCode.array;

/**
 * 45.跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 */
public class MinNumOfJump {

    //贪心算法从后往前找，选择距离最后一个位置最远的那个位置，也就是对应下标最小的那个位置
    public static int jumpNum(int[] nums){
        int lastIndex = nums.length - 1;
        int steps = 0;
        while (lastIndex > 0) {
            for (int i = 0; i < lastIndex; i++) {
                if (nums[i] >= lastIndex - i) {
                    steps++;
                    lastIndex = i;
                    break;
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jumpNum(nums));
    }

}
