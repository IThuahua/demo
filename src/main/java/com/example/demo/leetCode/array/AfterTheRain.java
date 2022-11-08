package com.example.demo.leetCode.array;

/**
 * https://leetcode.cn/problems/trapping-rain-water/description/
 */
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

    /**
     * https://leetcode.cn/problems/trapping-rain-water/solutions/1322689/jie-yu-shui-by-ursmile-g0vp/
     * @param height
     * @return
     */
    public static int trap1(int[] height){
        int res = 0, h1 = 0, h2 = 0;
        for (int i = 0, j = height.length -  1; i < height.length && j >= 0; i++, j--) {
            h1 = Math.max(h1, height[i]);
            h2 = Math.max(h2, height[j]);
            res += h1 + h2 - height[i];
        }
        return res - height.length * h1;
    }

    public static void main(String[] args) {
        int[] height = new int[]{2,0,2};
        System.out.println(trap(height));
        System.out.println(trap1(height));
    }
}
