package com.example.demo.concurrency.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程和线程池
 *
 * @Author zhoushenghua on 2019-05-25
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws Exception {

        String[] arrayStr = new String[]{"a","b","c","d","e","f","g","h","i","j","H","I","J","K","L","M","N","O","P","Q"};
        int arrLen = arrayStr.length;
        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(arrLen, arrLen, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for(int i=0; i<arrLen; i++){
            HandleStr handleStr = new HandleStr(arrayStr, i);
            threadPoolExecutor.execute(handleStr);
            System.out.println(Thread.currentThread().getName() + ":" + i + "," + arrayStr[i]);
        }
        threadPoolExecutor.shutdown();
        while(true){
            if(((ThreadPoolExecutor) threadPoolExecutor).getActiveCount() == 0){
                for (String s : arrayStr) {
                    System.out.print(s + ";");
                }
                break;
            }
        }
    }

    static class HandleStr implements Runnable{
        private String[] arrayStr;
        private int index;
        public HandleStr(String[] arrayStr, int index){
            this.arrayStr = arrayStr;
            this.index = index;
        }
        @Override
        public void run(){ //例如将所有的串变大写
            arrayStr[index] = arrayStr[index].toUpperCase();
        }
    }
}


/*

        //定长线程池
        ExecutorService fixedThreadPool1 = Executors.newFixedThreadPool(1);
        ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(1,new Factory());

        new ThreadPoolExecutor(3,5,0,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        //单线程池
        ExecutorService singleThreadPool1 = Executors.newFixedThreadPool(1);
        ExecutorService singleThreadPool2 = Executors.newFixedThreadPool(1,new Factory());

        new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        //缓存线程池
        ExecutorService cacheThreadPool1 = Executors.newCachedThreadPool();
        ExecutorService cacheThreadPool2 = Executors.newCachedThreadPool(new Factory());

        new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        //定长定时线程池
        ExecutorService scheduledThreadPool1 = Executors.newScheduledThreadPool(1);
        ExecutorService scheduledThreadPool2 = Executors.newScheduledThreadPool(1, new Factory());

        new ScheduledThreadPoolExecutor(new ThreadPoolExecutor(1, Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS, new DelayedWorkQueue()))

*/

//    static class Factory implements ThreadFactory {
//        @Override
//        public Thread newThread(Runnable r) {
//            return null;
//        }
//    }
