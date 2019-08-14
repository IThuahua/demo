package com.example.demo.leetCode.lru;

import java.util.HashMap;

/**
 * 哈希表+双向链表(前者记录键值对，后者处理LRU缓存机制)
 * @Author zhoushenghua on
 */
public class LRUCache {
    /*
     * 空节点为头结点,其next节点是实际意义上的头结点
     */
    private Node head = new Node();

    /*
     * 空节点为尾结点,其pre节点是实际意义上的尾结点
     */
    private Node tail = new Node();

    /*
     * 规定的存储节点容量
     */
    private int capacity;

    /*
     * 以存在的节点,小于容量时,新增节点尾头结点,否则,先删除尾部节点,再放置为头结点
     */
    private int size;
    /*
     * 哈希表记录键值对
     */
    private HashMap<Integer, Node> hashMap = new HashMap<>();

    public LRUCache(int capacity){
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        this.size = 0;
    }

    /*
     * 双向链表处理LRU缓存机制
     */
    class Node{
        private int key;
        private int value;
        private Node pre;
        private Node next;
        public Node(){}
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    /*
     * 将节点添加到虚拟头结点,次head
     */
    private void add(Node node){
        Node originHead = head.next;
        head.next = node;
        node.pre = head;
        node.next = originHead;
        originHead.pre = node;
    }

    /*
     * 删除某个节点
     */
    private void delete(Node node){
        Node preNode = node.pre;
        Node nextNode = node.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
        node.pre = null;
        node.next = null;
    }

    /*
     * 插入某个值
     */
    public void put(int key, int value){
        Node node = hashMap.get(key);
        if(node != null){
            node.value = value;
            delete(node);
            add(node);
        }else{
           if(size < capacity){
               size++;
           }else{
               Node delNode = tail.pre;
               hashMap.remove(delNode.key);
               delete(delNode);
           }
           Node newNode = new Node(key, value);
           add(node);
           hashMap.put(key, newNode);
        }
    }

     /*
      * 获取某个key的值
      */
     public int get(int key){
         Node node = hashMap.get(key);
         if(node == null){
             return -1;
         }
         delete(node);
         add(node);
         return node.value;
     }

}
