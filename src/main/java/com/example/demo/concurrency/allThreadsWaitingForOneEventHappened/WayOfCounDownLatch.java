package com.example.demo.concurrency.allThreadsWaitingForOneEventHappened;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 怎么实现所有线程在等待某个事件的发生才会去执行？
 * @Author zhoushenghua on
 */
public class WayOfCounDownLatch {

    private static final int N = 3;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(N);
        for(int i=1; i<=N; i++){
            pool.execute(new Thread(new ThreadHandler("task:" + i, countDownLatch)));
        }
        pool.shutdown();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("我是A,我不执行你们都tm等着");
                    Thread.sleep(5000);
                    System.out.println("好了,你们玩儿吧");
                    countDownLatch.countDown();
                }catch (InterruptedException e){
                }
            }
        });
        threadA.start();
    }

    static class ThreadHandler implements Runnable{
        private String name;
        private final CountDownLatch signal;

        public ThreadHandler(String name, CountDownLatch signal){
            this.name = name;
            this.signal = signal;
        }

        @Override
        public void run() {
            try{
                signal.await();
                System.out.println(name + " do something!" + System.currentTimeMillis());
            }catch (InterruptedException e){
            }
        }
    }


}
