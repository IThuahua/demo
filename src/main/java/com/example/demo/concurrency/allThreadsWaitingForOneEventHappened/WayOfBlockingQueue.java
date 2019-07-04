package com.example.demo.concurrency.allThreadsWaitingForOneEventHappened;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 怎么实现所有线程在等待某个事件的发生才会去执行？
 * @Author zhoushenghua on
 */
public class WayOfBlockingQueue {
    private static final int N = 3;
    public static void main(String[] args) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue();

        ExecutorService pool = Executors.newFixedThreadPool(N);

        for(int i=1; i<=N; i++){
            pool.execute(new Thread(new ThreadHandler("task:" + i, queue)));
        }
        pool.shutdown();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("我是A,我不执行你们都tm等着");
                    Thread.sleep(5000);
                    System.out.println("好了,你们玩儿吧");
                    List list = new ArrayList<>();
                    for(int i=1; i<=N; i++){
                        list.add(i);
                    }
                    queue.addAll(list);
                }catch (InterruptedException e){
                }
            }
        });
        threadA.start();
    }

    static class ThreadHandler implements Runnable{
        private String name;
        private LinkedBlockingQueue queue;
        public ThreadHandler(String name, LinkedBlockingQueue queue){
            this.name = name;
            this.queue = queue;
        }
        @Override
        public void run() {
            try{
                queue.take();
                System.out.println(name + " do something!" + System.currentTimeMillis());
            }catch (InterruptedException e){
            }
        }
    }


}
