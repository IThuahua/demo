package com.example.demo.cpuCache;

/**
 * @Author zhoushenghua on
 */
public class CpuCache {

    public static void main(String[] args) {
        int[] arr = new int[64 * 1024 * 1024];
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 3;
        }
        System.out.println(System.nanoTime() - start);

        long start2 = System.nanoTime();
        for (int i = 0; i < arr.length; i += 16) {
            arr[i] *= 3;
        }
        System.out.println(System.nanoTime() - start2);
    }


}
