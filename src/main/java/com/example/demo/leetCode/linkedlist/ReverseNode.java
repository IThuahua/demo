package com.example.demo.leetCode.linkedlist;


/**
 * 链表反转
 * @Author zhoushenghua on
 */
public class ReverseNode {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        System.out.println(node);

        ListNode listNode = reverseListSec(node);
        System.out.println(listNode);


        System.out.println(reverseBetween(node, 2, 4));
    }

    //反转m~n之间的元素,将链表分为三部分，第一部分和第三部分不用动，将之间部分反转，然后将三部分按顺序组装即可。
    public static ListNode reverseBetween(ListNode head, int m, int n ){
        //待写
        return null;
    }



    //递归法
    public static ListNode reverseListSec(ListNode head){
        return reverse(null, head);
    }

    public static ListNode reverse(ListNode pre, ListNode cur){
        if(cur == null){
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(cur,next);
    }

    //迭代法
    public static ListNode reverseList(ListNode head){
        ListNode result = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = result;
            result = cur;
            cur = next;
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
