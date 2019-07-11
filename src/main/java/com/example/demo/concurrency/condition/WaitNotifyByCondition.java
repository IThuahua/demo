package com.example.demo.concurrency.condition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * conditionB未被唤将一直阻塞
 * @Author zhoushenghua on
 */
public class WaitNotifyByCondition {

    static ReentrantLock lock = new ReentrantLock();
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new WaitA(), "waitThreadA");
        threadA.start();
        Thread threadB = new Thread(new WaitB(), "waitThreadB");
        threadB.start();
        TimeUnit.SECONDS.sleep(5);
        lock.lock();
        try{
            conditionA.signal();
        }finally {
            lock.unlock();
        }
    }

    static class WaitA implements Runnable{
        @Override
        public void run(){
            lock.lock();
            try{
                System.out.println(Thread.currentThread() + "begin await @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                conditionA.await();
                System.out.println(Thread.currentThread() + "end await @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    static class WaitB implements Runnable{
        @Override
        public void run(){
            lock.lock();
            try{
                System.out.println(Thread.currentThread() + "begin await @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                conditionB.await();
                System.out.println(Thread.currentThread() + "end await @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }
}
