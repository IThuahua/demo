package com.example.demo.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * @Author zhoushenghua
 * @Description  位图（Bitmap），即位（Bit）的集合，是一种数据结构，可用于记录大量的0-1状态，在很多地方都会用到，比如Linux内核（如inode，磁盘块）、
 * Bloom Filter算法等，其优势是可以在一个非常高的空间利用率下保存大量0-1状态。
 * 适用场景：大量数据排序、去重，特别是超过内存大小的数据量
 * https://blog.csdn.net/qq_34486648/article/details/122332132
 * @Date 25.10.22
 */
public class BitMapDemo {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(2,2,2,2,1,2,3,4,5,6,7,8,9,3,5,7);
        System.out.println("去重后结果：" + remove_duplicate(arr));
        System.out.println("重复的所有元素：" + get_duplicate(arr, false));
        System.out.println("重复的元素：" + get_duplicate(arr, true));
        System.out.println("去重后排序结果：" + remove_duplicate_sort(arr));
    }

    //去重后结果
    private static List<Integer> remove_duplicate(List<Integer> arr) {
        BitSet bitSet = new BitSet();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!bitSet.get(arr.get(i))) {
                result.add(arr.get(i));
            }
            bitSet.set(arr.get(i));
        }
        return result;
    }

    //重复元素
    private static List<Integer> get_duplicate(List<Integer> arr, boolean noduplicate) {
        BitSet bitSet = new BitSet();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (bitSet.get(arr.get(i))) {
                if (noduplicate && result.contains(arr.get(i))) {
                    continue;
                }
                result.add(arr.get(i));
            } else {
                bitSet.set(arr.get(i));
            }
        }
        return result;
    }

    //去重后排序结果
    private static List<Integer> remove_duplicate_sort(List<Integer> arr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < arr.size(); i++) {
            bitSet.set(arr.get(i));
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i)) {
                result.add(i);
            }
        }
        return result;
    }

}
