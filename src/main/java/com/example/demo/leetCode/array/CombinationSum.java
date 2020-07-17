package com.example.demo.leetCode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [[7],[2,2,3]]
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [[2,2,2,2],[2,3,3],[3,5]]
 */
public class CombinationSum {

    private static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates.length == 0 || target < 0) {
            return result;
        }
        Arrays.sort(candidates);

        List<Integer> list = new ArrayList<>();
        process(0, candidates, target, list);
        return result;

    }

    private static void process(int start, int[] candidates, int target, List<Integer> list) {
        //递归的终止条件
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                process(i, candidates, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }

    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;

        System.out.println(combinationSum(candidates, target));
    }

}
