package com.example.demo.concurrency.condition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * wait()方法被执行后,锁立即被释放。但notify方法被执行后，必须等到执行完notifyu方法所在同步代码块后才释放锁
 * @Author zhoushenghua on
 */
public class WaitNotify {

    //下列两行顺序可能颠倒,因为是竞争获取锁的
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(),"waitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run(){
            synchronized (lock){
                while (flag){
                    try{
                        System.out.println(Thread.currentThread() + "flag is true.await @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()  + "flag is false.running @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run(){
            //此处进行锁粗化，三个锁合并成一个锁。但执行完run()方法体所有代码notifyAll才会释放锁
            synchronized (lock){
                System.out.println(Thread.currentThread() + "flag is true.notify @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + "flag is true.sleep @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
            synchronized (lock){
                SleepUtils.second(5);
            }
            System.out.println("我也算同步块内呢!!@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    static class SleepUtils{
        public static void second(int n){
            try{
                TimeUnit.SECONDS.sleep(n);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
