package com.example.demo.leetCode.linkedlist;

/**
 * 判断链表是否有环：给定一个链表，判断链表中是否有环，即链表的某个节点是之前的某个节点。
 *
 * 问题扩展1：如果有环，如何求出环的长度？
 * 问题扩展2：如果又换，如何求出入环节点？
 */
public class HasCycle {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(hasCycle(node1));
        System.out.println(cycleLength(node1));
        System.out.println(enterPointNode(node1));

    }

    /**
     * Folyd环判定算法：快慢指针遍历链表，快指针步距为2，慢指针步距为1，如果存在环，则两个指针一定会在环中相遇。
     * 原理：类似绕操场跑步，两个人同时出发，只要他们只要一直跑（绕圈），则跑的快的一定会和跑得慢的相遇。
     * (1).若果链表为空，或只有一个节点，则没有环，直接返回；
     * (2).创建快、慢指针，初始化都指向头结点。两者依其步距前进获取节点，有空时，则说明有next节点为null，说明没有环；
     * (3).如果两者依其步距前进获取节点，一直没有空，则当两则相遇说明有环。
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head){
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        //没有环的话，肯定有next为null的节点，并不会死循环
        while (p1.next != null && p2.next != null && p2.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     * 问题扩展1：如果有环，如何求出环的长度？
     * 当首次相遇，则说明有环，让两个指针继续循环前进，并统计次数，再次相遇时，由于两者步差是1，则fast比slow多走了一整圈
     * @param head
     * @return
     */
    public static int cycleLength(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        int result = 0;
        while (slow.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                while (true){
                    slow = slow.next;
                    fast = fast.next.next;
                    result++;
                    if(slow == fast){
                        return result;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 设链表头至入环点距离为D，入环点至首次相遇点距离是S1，首次相遇点回到入环点距离为S2。
     * 则首次相遇时，slow(一次一步)所走距离为D+S1，fast(一次两步)所走距离为D+S1+n(S1+S2)。多走了n圈。
     * 而fast速度时slow的2倍，距离也是2倍，即2*(D+S1)=D+S1+n(S1+S2)。
     * 整理得：D=(n-1)*(S1+S2)+S2。即等于首次相遇点绕环n-1圈再回到入环点的距离
     * 所以只需把一个指针放在头节点，一个指针放在首次相遇点，两个指针步长都为1，相遇的节点即是入环点。
     * @param head
     * @return
     */
    public static ListNode enterPointNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        ListNode p1 = head;
        //此处作初始化，因为前提是有环
        ListNode p2 = new ListNode(0);
        while (slow.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                p2 = slow;
                break;
            }
        }

        while (p1.next != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
            if(p1 == p2){
                return p1;
            }
        }
        return null;
    }



    static class ListNode{
        int num;
        ListNode next;
        public ListNode(int num){
            this.num = num;
        }

        @Override
        public String toString() {
            return "num=" + num ;
        }
    }

}
