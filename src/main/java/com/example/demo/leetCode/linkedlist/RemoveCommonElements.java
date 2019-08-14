package com.example.demo.leetCode.linkedlist;

/**
 *
 * @Author zhoushenghua on
 */
public class RemoveCommonElements {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(1);
        node.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next.next = new ListNode(2);
        System.out.println(node);

        ListNode listNode = removeCommonElements(node);
        System.out.println(listNode);


        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(2);
        node1.next.next.next = node1;
        System.out.println(hasCycle(node1)?"有":"无");

    }

    /*
     * 判断链表是否有环,此处用Floyd环判定算法
     */
    public static boolean hasCycle(ListNode head){
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }


    /*
     * 删除未排序链表中,所有重复的元素,使每个元素值出现一次,如：输入1 -> 1 -> 3 -> 2 -> 1；输出1 -> 3 -> 2。
     */
    public static ListNode removeCommonElements(ListNode head){
        if(head == null){
            return head;
        }
        //构建start为保存head完整数据
        ListNode start = head;
        //构建judgePre为删除值一样的节点
        ListNode judgePre = start;
        ListNode judge = start.next;
        while (start != null && start.next != null){
            //单次遍历,删掉与首节点值一样的节点
            while(judge != null){
                int value = start.val;
                if(judge.val == value){
                    judgePre.next = judge.next;
                    judge = judge.next;
                }else{
                    judgePre = judge;
                    judge = judge.next;
                }
            }
            start = start.next;
            judgePre = start;
            judge = start.next;
        }
        return head;
    }

    /*
     * 删除排序链表中,所有重复的元素,只保留单独的元素,如：输入1 -> 2 -> 2 -> 3 -> 3 -> 4；输出1 -> 4。
     */
    public static ListNode removeSortedCommonAllElements(ListNode head){
        if(head == null){
            return head;
        }
        //在首节点前加一个节点,方便首节点被删除
        ListNode helper = new ListNode(0);
        helper.next = head;
        head = helper;
        while (head.next != null && head.next.next != null){
            if(head.next.val == head.next.next.val){
                int value = head.next.val;
                while(head.next != null && head.next.val == value){
                    head.next = head.next.next;
                }
            }else{
                head = head.next;
            }
        }
        return helper.next;
    }

    /*
     * 删除排序链表中,所有重复的元素,使每个元素值出现一次,如：输入1 -> 1 -> 2 -> 3 -> 3；输出1 -> 2 -> 3。
     */
    public static ListNode removeSortedCommonElements(ListNode head){
        ListNode pre = head;
        while(pre != null && pre.next != null){
            if(pre.val == pre.next.val){
                pre.next = pre.next.next;
            }else{
                pre = pre.next;
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
