package com.example.demo.other;

/**
 * @Author zhoushenghua
 * @Description https://blog.csdn.net/cj1561435010/article/details/111415853?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-1.pc_relevant_paycolumn_v3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-1.pc_relevant_paycolumn_v3&utm_relevant_index=2
 * @Date 18.4.22
 */
public class HandWrittenBloomFilter<T> {

    private int bitSize;
    private long[] bits;
    private int hashSize;

    /**
     * 布隆过滤器构造函数: 各项值是通过数据计算得出的与数据规模和误判率的关系表达式
     *
     * @param numberSize 数据规模
     * @param fpp        误判率
     */
    public HandWrittenBloomFilter(int numberSize, double fpp) {
        // guava中的为：(int) (-numberSize * Math.log(fpp) / (Math.log(2) * Math.log(2)));
        this.bitSize = (int) (-numberSize * Math.log(fpp) / Math.pow(Math.log(2), 2));
        // guava中的为：Math.max(1, (int) Math.round((double) bitSize / numberSize * Math.log(2)))
        this.hashSize = (int) (bitSize * Math.log(2) / numberSize);
        this.bits = new long[(int) Math.ceil(this.bitSize >> 6)];
    }


    public boolean put(T value) {
        int hashCode1 = value.hashCode();
        int hashCode2 = hashCode1 >> 16;
        boolean result = false;
        for (int i = 0; i < hashSize; i++) {
            int combineHash = hashCode1 + (i * hashCode2);
            if (combineHash < 0) {
                // ~表示非运算符，就是将该数的所有二进制位全取反。(~x) = -(x + 1)
                combineHash = ~combineHash;
            }
            int index = combineHash % bitSize;
            if (set(index)) {
                result = true;
            }
        }
        return result;
    }

    public boolean contains(T value) {
        int hashCode1 = value.hashCode();
        int hashCode2 = hashCode1 >> 16;
        for (int i = 0; i < hashSize; i++) {
            int combineHash = hashCode1 + (i * hashCode2);
            if (combineHash < 0) {
                combineHash = ~combineHash;
            }
            int index = combineHash % bitSize;
            if (!get(index)) {
                return false;
            }
        }
        return true;
    }

    private boolean set(int index) {
        boolean result = false;
        int numbersIndex = index / Long.SIZE;
        int numberIndex = index % Long.SIZE;
        // 获取某个位的数字，只需要和该位数字位1的其他位数字都为0的书进行按位与运算即可
        result = (bits[numbersIndex] & (1L << numberIndex)) == 0;
        // 将某个位的数字设置为1，只需要和该位数字为1其他位数字都为0的数进行按位或运算即可
        bits[numbersIndex] |= (1L << numberIndex);
        return result;
    }

    private boolean get(int index) {
        int numbersIndex = index / Long.SIZE;
        int numberIndex = index % Long.SIZE;
        return (bits[numbersIndex] & (1L << numberIndex)) != 0;
    }

}
