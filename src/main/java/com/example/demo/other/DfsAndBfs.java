package com.example.demo.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author zhoushenghua
 * @Description 深度优先遍历(DFS)和广度优先遍历(BFS)
 * @Date 18.4.22
 */
public class DfsAndBfs {


    static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 递归形式DFS
    public static void dfsForRecursion(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        dfsForRecursion(root.left, result);
        dfsForRecursion(root.right, result);
    }

    // 非递归形式DFS
    public static void dfsForNotRecursion(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tree = stack.pop();
            result.add(tree.val);
            if (tree.right != null) {
                stack.push(tree.right);
            }
            if (tree.left != null) {
                stack.push(tree.left);
            }
        }
    }

    //BFS
    public static ArrayList<Integer> BfsTree(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode tree = queue.poll();
            if (tree.left != null) {
                queue.offer(tree.left);
            }
            if (tree.right != null) {
                queue.offer(tree.right);
            }
            list.add(tree.val);
        }
        return list;
    }


    // 构造的图，                       A(1，左B，右C)，
    //             B(2，左D，右E)，                         C(3，左F，右G)，
    // D(1，左null，右null)，E(1，左null，右null)  F(1，左null，右null)，G(1，左null，右null)
    public static TreeNode getNodeA() {
        TreeNode nodeD = new TreeNode(4);
        TreeNode nodeE = new TreeNode(5);
        TreeNode nodeF = new TreeNode(6);
        TreeNode nodeG = new TreeNode(7);
        TreeNode nodeB = new TreeNode(2, nodeD, nodeE);
        TreeNode nodeC = new TreeNode(3, nodeF, nodeG);
        TreeNode nodeA = new TreeNode(1, nodeB, nodeC);
        return nodeA;
    }

    public static void main(String[] args) {

        ArrayList<Integer> result1 = new ArrayList<>();
        dfsForRecursion(getNodeA(), result1);
        System.out.println(result1);

        ArrayList<Integer> result2 = new ArrayList<>();
        dfsForNotRecursion(getNodeA(), result2);
        System.out.println(result2);

        System.out.println(BfsTree(getNodeA()));
    }

}
