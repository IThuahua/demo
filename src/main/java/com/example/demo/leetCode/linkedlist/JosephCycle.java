package com.example.demo.leetCode.linkedlist;

/**
 * 约瑟夫问题
 * @Author zhoushenghua on
 */
public class JosephCycle {

    public static void main(String[] args) {
        CycleLink cycleLink = new CycleLink(7,3,3);
        cycleLink.create();
        cycleLink.play();
    }

    static class CycleLink{
        Node head = null;//创建时的首节点
        Node cycle;//环
        int len;//总人数
        int m;//第m个人开始
        int k;//报k的人出列

        public CycleLink(int len, int m, int k) {
            this.len = len;
            this.m = m;
            this.k = k;
        }

        //构建环形单向链表
        public void create(){
            for(int i=1; i<=len; i++){
                if(i == 1){
                    Node node = new Node(i);
                    this.head = node;
                    cycle = node;
                }else if(i == len){
                    Node node = new Node(i);
                    cycle.next = node;
                    cycle = node;
                    //最后一个节点的下一个节点为首节点,才能形成环
                    cycle.next = head;
                }else{
                    Node node = new Node(i);
                    cycle.next = node;
                    cycle = node;//位置后移
                }
            }
        }

        //开始报数,报到k的人出列
        public void play(){
            //找到第m个人
            cycle = head;

            for(int i=1; i<m; i++){
                cycle = cycle.next;
            }
            //直到只剩一个节点
            while(len != 1){
                //报到m-1
                for(int i=1; i<m-1; i++){
                    cycle = cycle.next;
                }
                System.out.println("删除节点为:" + cycle.next.num);
                //将下一个节点删掉
                cycle.next = cycle.next.next;
                //从下一个人开始报数
                cycle = cycle.next;
                this.len--;
            }
            System.out.println("最后节点为:" + cycle.num);
        }
    }

    static class Node{
        int num;
        Node next;
        public Node(int num){
            this.num = num;
        }
    }

}
