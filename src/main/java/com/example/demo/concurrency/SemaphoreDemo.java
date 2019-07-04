package com.example.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * java信号灯
 *
 * @Author zhoushenghua on 2019-06-29
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //Semaphore concurrency = new Semaphore(1,true);

        Semaphore semaphore = new Semaphore(2);
        for(int i = 0; i<6; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("线程:" + Thread.currentThread().getName() + "进入,已有" + (2-semaphore.availablePermits()) + "并发");

                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "即将离开");

                    semaphore.release();
                    System.out.println("线程" + Thread.currentThread().getName() + "已经离开,当前并发数为:" + (2-semaphore.availablePermits()));
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

    }

}
