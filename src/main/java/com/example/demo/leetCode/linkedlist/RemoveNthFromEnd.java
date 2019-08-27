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
        if(n < 0){
            return head;
        }
        ListNode temp = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int i = 1;
        while (temp != null){
            map.put(i, temp);
            i++;
            temp = temp.next;
        }
        if(map.size() == 1 && n == 1){
            return null;
        }else if(map.size() < n){
            return head;
        }else if(map.size() == n){
            return head.next;
        }else{
            map.get(map.size()-n).next = map.get(map.size()-n+2);
        }
        return head;
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
