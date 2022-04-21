package com.example.demo.concurrency.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhoushenghua
 * @Description https://www.acfun.cn/a/ac15405661
 * @Date 20.4.22
 */
public class ForkJoinTaskDemo extends RecursiveTask<Integer> {

    private static final int INTERVAL_THRESHOLD = 1; //间隔阈值

    private int startIndex;
    private int endIndex;
    public AtomicInteger taskNum; //该参数可删除

    public ForkJoinTaskDemo(int startIndex, int endIndex, AtomicInteger taskNum) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.taskNum = taskNum;
    }

    public AtomicInteger getTaskNum() {
        return taskNum;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (endIndex - startIndex) <= INTERVAL_THRESHOLD;
        if (canCompute) {
            taskNum.getAndIncrement();
            System.out.println("startIndex:" + startIndex + ", endIndex:" + endIndex);
            for (int i = startIndex; i <= endIndex; i++) {
                sum += i;
            }
        } else {
            int middle = (startIndex + endIndex) / 2;
            ForkJoinTaskDemo leftTask = new ForkJoinTaskDemo(startIndex, middle, taskNum);
            ForkJoinTaskDemo rightTask = new ForkJoinTaskDemo(middle + 1, endIndex, taskNum);

            leftTask.fork();
            rightTask.fork();

            Integer rightResult = rightTask.join();
            Integer leftResult = leftTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        ForkJoinTaskDemo forkJoinTaskDemo = new ForkJoinTaskDemo(1, 100, new AtomicInteger(0));
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = pool.submit(forkJoinTaskDemo);
        System.out.println("结果值:" + submit.get());
        System.out.println("任务总个数:" + forkJoinTaskDemo.getTaskNum().get());
    }
}
