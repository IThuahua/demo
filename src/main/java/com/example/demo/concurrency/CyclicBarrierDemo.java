package com.example.demo.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 允许一组线程相互等待到达一个共同的障碍点
 * 实例:假设每个线程代表一个运动员,当所有运动员都准备好了,才一起出发,否则大家都等待
 * @Author zhoushenghua on
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
//        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("12465");
//            }
//        });
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new Thread(new Runner(barrier, "张三")));
        executorService.execute(new Thread(new Runner(barrier,"李四")));
        executorService.execute(new Thread(new Runner(barrier, "王五")));

        executorService.shutdown();

    }

    static class Runner implements Runnable{
        private CyclicBarrier barrier;
        private String name;

        Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                System.out.println(name + " has been prepared!");
                barrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println(name + " go go go!!!");
        }
    }

}
