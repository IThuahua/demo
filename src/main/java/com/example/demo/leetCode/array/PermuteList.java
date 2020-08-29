package com.example.demo.leetCode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class PermuteList {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length == 0) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[length];
        dfs(nums, length, 0, path, used, result);
        return result;
    }

    private void dfs(int[] nums, int length, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        if (depth == length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (used[i]) {
              continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, length, depth + 1, path, used, result);
            path.removeLast();
            used[i] = false;
        }
    }

}
