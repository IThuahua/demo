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
       if (head == null) {
            return null;
        }

        //结果，需要将链表最头部的元素放在最尾部
        ListNode result = null;
        //当前反转的节点
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next; //下一个要反转的节点
            current.next = result; //把之前已反转的链表追加到当前节点后面，实现当前节点的反转
            result = current; //当前节点就是包含当前节点的已经反转好后的链表了
            current = next;  //要反转的节点变更为下一个。
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
