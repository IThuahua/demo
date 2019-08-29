package com.example.demo.leetCode.linkedlist;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @Author zhoushenghua on
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        System.out.println(node);
        System.out.println(swapPairs(node));

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        System.out.println(node1);
        System.out.println(swapPairs2(node1));
    }

    public static ListNode swapPairs(ListNode head){
        ListNode node = head;

        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode pre = temp;
        while (node != null && node.next != null){
            ListNode t1 = node.next;
            node.next = node.next.next;
            t1.next = node;

            pre.next = t1;
            pre = node;
            node = node.next;
        }
        return temp.next;
    }

    public static ListNode swapPairs2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode temp = head.next;
        head.next = swapPairs2(temp.next);
        temp.next = head;
        return temp;
    }



    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
