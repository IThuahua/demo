package com.example.demo.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 允许一个或多个线程等待,直到其他线程都执行完,
 * @Author zhoushenghua on
 */
public class CountDownLatchDemo {
    private static final int N = 2;

    public static void main(String[] args) throws Exception {
        CountDownLatch downSignal = new CountDownLatch(N);
        CountDownLatch startSignal = new CountDownLatch(1);

        try{
            for(int i=1; i<=N; i++){
                new Thread(new ThreadHandler(i, downSignal, startSignal)).start();
            }
            System.out.print("**begin**");
            startSignal.countDown();
            downSignal.await();
            System.out.print("**OK**");
        }catch (InterruptedException e ){
            e.printStackTrace();
        }

    }

    static class ThreadHandler implements Runnable{
        private final CountDownLatch doneSignal;
        private final CountDownLatch startSignal;
        private int beginIndex;

        ThreadHandler(int beginIndex, CountDownLatch doneSignal, CountDownLatch startSignal){
            this.doneSignal = doneSignal;
            this.startSignal = startSignal;
            this.beginIndex = beginIndex;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                beginIndex = (beginIndex - 1) * 10 + 1;
                for (int i = beginIndex; i<beginIndex+10; i++){
                    System.out.print(i + ",");
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                doneSignal.countDown();
            }
        }
    }



}
