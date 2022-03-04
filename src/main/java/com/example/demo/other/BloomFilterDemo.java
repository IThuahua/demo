package com.example.demo.other;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author zhoushenghua
 * @Description https://www.jianshu.com/p/2f20044eed1f
 * @Date 4.3.22
 */
public class BloomFilterDemo {

    //预计要插入的数据量
    private static int recordNum = 100000;

    //期望误判率
    private static double fpp = 0.01;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), recordNum, fpp);

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        for (int i = 2; i < recordNum; i++) {
            // 向布隆过滤器添加数据，类似HashMap.put(key, data)方法
            bloomFilter.put(i);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < recordNum; i++) {
            // 如果布隆过滤器存在对应元素，类似HashMap.contains(key)方法
            if (bloomFilter.mightContain(i)) {

            } else {
                System.out.println( i + "误判了");
                count++;
            }
        }
        System.out.println("误判数：" + count);
        System.out.println(recordNum + "次查询耗时：" + (System.currentTimeMillis() - time));


    }



}
