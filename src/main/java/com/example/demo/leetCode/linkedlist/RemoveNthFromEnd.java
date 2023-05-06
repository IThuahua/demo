package com.example.demo.leetCode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * @Author zhoushenghua on
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        System.out.println(node);
        System.out.println(removeNthFromEnd(node, 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n){
        if (head == null || n <= 0) {
            return head;
        }
        int count = 0; //总长度
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        ListNode result = new ListNode(0);
        result.next = head;
        ListNode current = result; //当前节点位置，其后节点需要删除
        for (int i = 0; i < count - n; i++) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next; //删除节点
        }
        return result.next;
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
