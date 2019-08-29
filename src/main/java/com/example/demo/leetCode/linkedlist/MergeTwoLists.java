package com.example.demo.leetCode.linkedlist;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @Author zhoushenghua on
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        System.out.println("l1:" + l1);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.println("l2:" + l2);
        System.out.println(mergeTwoLists(l1, l2));

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        System.out.println("l3:" + l3);

        ListNode[] lists = {l1, l2, l3};
        System.out.println(mergeKLists2(lists));

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (l1 != null && l2 != null){
            if(l1.val > l2.val){
                temp.next = new ListNode(l2.val);
                temp = temp.next;
                l2 = l2.next;
            }else{
                temp.next = new ListNode(l1.val);
                temp = temp.next;
                l1 = l1.next;
            }
        }
        if(l1 != null){
            temp.next = l1;
        }
        if(l2 != null){
            temp.next = l2;
        }
        return result.next;
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * 示例:
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     */
    public static ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        ListNode node = null;
        for (int i = 0; i < lists.length; i++) {
            ListNode temp = mergeTwoLists(node, lists[i]);
            node = temp;
        }
        return node;
    }

    public static ListNode mergeKLists2(ListNode[] lists){
        if(lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        if(lists.length == 2){
            return mergeTwoLists(lists[0], lists[1]);
        }
        int mid = lists.length/2;
        ListNode[] temp1 = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            temp1[i] = lists[i];
        }
        ListNode[] temp2 = new ListNode[lists.length-mid];
        for(int i = mid,j=0; i < lists.length; i++,j++){
            temp2[j] = lists[i];
        }
        return mergeTwoLists(mergeKLists2(temp1), mergeKLists2(temp2));
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
