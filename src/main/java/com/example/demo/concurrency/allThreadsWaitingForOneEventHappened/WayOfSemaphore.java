package com.example.demo.concurrency.allThreadsWaitingForOneEventHappened;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 怎么实现所有线程在等待某个事件的发生才会去执行？
 * @Author zhoushenghua on
 */
public class WayOfSemaphore {

    private static final int N = 3;
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(N);
        Semaphore semaphore = new Semaphore(N);
        try{
            semaphore.acquire(N);
        }catch (InterruptedException e){
        }
        for(int i=1; i<=N; i++){
            pool.execute(new ThreadHandler("task:" + i, semaphore));
        }
        pool.shutdown();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("我是A,我不执行你们都tm等着");
                    Thread.sleep(5000);
                    System.out.println("好了,你们玩儿吧");
                    semaphore.release(N);
                }catch (InterruptedException e){
                }
            }
        });
        threadA.start();
    }

    static class ThreadHandler implements Runnable{
        private String name;
        private Semaphore semaphore;
        public ThreadHandler(String name, Semaphore semaphore){
            this.name = name;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try{
                semaphore.acquire();
                System.out.println(name + " do something!" + System.currentTimeMillis());
            }catch (InterruptedException e){
            }

        }
    }



}
