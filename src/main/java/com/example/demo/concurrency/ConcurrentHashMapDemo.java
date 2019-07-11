package com.example.demo.concurrency;

import sun.nio.ch.ThreadPool;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhoushenghua on
 */
public class ConcurrentHashMapDemo {


    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(2);
        Thread t = new Thread(() ->{
            for(int i=0; i<1000; i++){
                new Thread(()->map.put(UUID.randomUUID().toString(), ""),"child_thread_" + i).start();
            }
        }, "parent_thread");
        t.start();
        t.join();

    }









/*    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String,AtomicInteger> map = new ConcurrentHashMap<>();
        map.put("key", new AtomicInteger(1));

        ExecutorService pool = Executors.newFixedThreadPool(1000);
        for(int i=0; i<1000; i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    map.get("key").incrementAndGet();
                }
            });
        }
        //Thread.sleep(3000);
        pool.shutdown();
        while (true){
            if(((ThreadPoolExecutor)pool).getActiveCount() == 0){
                System.out.println(map.get("key"));
                return;
            }
        }
    }*/
}
