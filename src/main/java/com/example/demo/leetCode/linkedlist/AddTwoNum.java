package com.example.demo.leetCode.linkedlist;

/**
 * 给出两个 非空 的链表用来表示两个 非负 的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 如:
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @Author zhoushenghua on
 */
public class AddTwoNum {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(1);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNum(l1, l2);
        System.out.println(result);

    }

    public static ListNode addTwoNum(ListNode l1, ListNode l2){
        ListNode t1 = l1;
        ListNode t2 = l2;
        int nextAddNum = 0;
        while(t1 != null && t2 != null){
            //补齐位数,末尾为高位,补0
            if(t1.next == null && t2.next != null){
                t1.next = new ListNode(0);
            }
            if(t2.next == null && t1.next != null){
                t2.next = new ListNode(0);
            }

            int sum = nextAddNum + t1.val + t2.val;
            //t1与l1同一指针,即此处修改l1的对应位的值
            t1.val = sum%10;
            nextAddNum = sum/10;
            //防止高位相加超过9
            if(t1.next == null && t2.next == null && nextAddNum != 0){
                t1.next = new ListNode(nextAddNum);
            }
            t1 = t1.next;
            t2 = t2.next;
        }
        return l1;
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
