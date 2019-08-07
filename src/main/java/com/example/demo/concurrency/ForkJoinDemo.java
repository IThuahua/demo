package com.example.demo.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author zhoushenghua on
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;
    private static final long THRESHOLD = 1000;

    public ForkJoinDemo(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start <= THRESHOLD){
            long sum = 0;
            for(long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end)/2;
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(start, mid);
            forkJoinDemo1.fork();

            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(mid+1, end);
            forkJoinDemo2.fork();

            return forkJoinDemo1.join() + forkJoinDemo2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long sum = forkJoinPool.invoke(new ForkJoinDemo(1,1000000L));
        System.out.println(sum);

    }
}
