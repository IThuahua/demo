package com.example.demo.leetCode.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用LinkedHashMap
 * @Author zhoushenghua on
 */
public class LRUCacheByLHM {

    private int capacity;

    private LRULinkedHashMap<Integer, Integer> lruLinkedHashMap = new LRULinkedHashMap<>();

    private class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V>{
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
            if(size() >capacity){ //size()调用的是HashMap的方法
                return true;
            }else{
                return false;
            }
        }
    }

    public LRUCacheByLHM(int capacity){
        this.capacity = capacity;
    }

    public int get(int key){
        Integer value = lruLinkedHashMap.get(key);
        if(value == null){
            return -1;
        }
        lruLinkedHashMap.remove(key);
        lruLinkedHashMap.put(key, value);
        return value;
    }

    public void put(int key, int value){
        if(lruLinkedHashMap.containsKey(key)){
            lruLinkedHashMap.remove(key);
        }
        lruLinkedHashMap.put(key, value);
    }
}
