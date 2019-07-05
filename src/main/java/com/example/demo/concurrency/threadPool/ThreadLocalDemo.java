package com.example.demo.concurrency.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhoushenghua on
 */
public class ThreadLocalDemo {
    private static int a = 0;

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(a);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    a = threadLocal.get() + 1;
                    threadLocal.set(a);
                }
            });
        }
        executorService.shutdown();
        System.out.println(a);

    }




}
