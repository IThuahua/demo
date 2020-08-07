package com.example.demo.leetCode.array;

public class AfterTheRain {

    public static int trap(int[] height){
        if (height == null || height.length < 3) {
            return 0;
        }
        //计算每一个位置的雨滴数的和即可
        int sum = 0;

        //找某个柱子左侧最高的柱子数组
        int[] leftHeightest = new int[height.length];
        leftHeightest[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftHeightest[i] = leftHeightest[i-1] > height[i] ? leftHeightest[i-1] : height[i];
        }

        //找某个柱子右侧最高的柱子数组
        int[] rightHeightest = new int[height.length];
        rightHeightest[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightHeightest[i] = rightHeightest[i+1] > height[i] ? rightHeightest[i+1] : height[i];
        }

        //当前柱子左右两侧最高柱子中的较小者与当前柱子高度差即为当前柱子可装的水滴数
        for (int i = 1; i < height.length - 1; i++) {
            sum += Math.min(leftHeightest[i], rightHeightest[i]) - height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[]{2,0,2};
        System.out.println(trap(height));
    }
}
