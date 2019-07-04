package com.example.demo.concurrency.allThreadsWaitingForOneEventHappened;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 怎么实现所有线程在等待某个事件的发生才会去执行？
 * @Author zhoushenghua on
 */
public class WayOfCyclicBarrier {

    private static final int N = 3;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(N);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<= N; i++){
                    pool.execute(new Thread(new ThreadHandler("task:" + i)));
                }
                pool.shutdown();
            }
        });

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("我是A,我不执行你们都tm等着");
                    Thread.sleep(5000);
                    System.out.println("好了,你们玩儿吧");
                    cyclicBarrier.await();
                }catch (InterruptedException e){
                }catch (BrokenBarrierException e){
                }
            }
        });
        threadA.start();
    }

    static class ThreadHandler implements Runnable{
        private String name;
        public ThreadHandler(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name + " do something!" + System.currentTimeMillis());
        }
    }

}
