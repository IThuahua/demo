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
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = new ListNode(0); //结果链表，参与运算
        ListNode resultTail = result;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                l1 = new ListNode(0);
            }
            if (l2 == null) {
                l2 = new ListNode(0);
            }
            int sum = l1.val + l2.val + resultTail.val;
            resultTail.val = sum % 10; //更新当前位数值
            if (l1.next == null && l2.next == null && sum / 10 == 0) {
                break;
            }
            resultTail.next = new ListNode(sum / 10); //更新进位数值，并参与到进位的运算
            resultTail = resultTail.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        return result;
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
