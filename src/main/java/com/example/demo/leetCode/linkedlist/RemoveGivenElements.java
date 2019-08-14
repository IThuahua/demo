package com.example.demo.leetCode.linkedlist;

/**
 * 删除链表中等于给定值val的所有节点，如：输入1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6，val = 6；输出1 -> 2 ->3 -> 4 -> 5
 * @Author zhoushenghua on
 */
public class RemoveGivenElements {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(6);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next.next = new ListNode(6);
        System.out.println(node);

        ListNode listNode = removeElements(node, 6);
        System.out.println(listNode);
    }

    public static ListNode removeElements(ListNode head, int val){
        //首先删除首节点等于val的节点
        if(head == null){
            return head;
        }
        while(head != null && head.val == val){
            head = head.next;
        }
        if(head == null){ //到末尾了
            return head;
        }
        //present最开始为首节点,一直都不为空, judge为其下一个需判断的节点
        ListNode present = head;
        ListNode judge = head.next;
        while(judge != null){
            //处理最后一个节点
            if(judge.next == null){
                if(judge.val != val){
                    break;
                }
                present.next = null;
                break;
            }
            //删除中间节点等于val的节点
            //如果遇到等于val的节点，当前节点的下一个节点用其下一个节点替换(则交换当前节点与下一个节点的val的值，然后把下一个节点删掉即可)
            if(judge.val == val){
                present.next = judge.next;
                judge = present.next;
            }else{
                present = judge;
                judge = judge.next;
            }
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
