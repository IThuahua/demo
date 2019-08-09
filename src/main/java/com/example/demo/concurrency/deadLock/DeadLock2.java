package com.example.demo.concurrency.deadLock;

/**
 * @Author zhoushenghua on
 */
public class DeadLock2 {
    public static void main(String[] args) throws Exception {
        Object objA = new Object();
        Object objB = new Object();
        Object objC = new Object();
        Thread t1 = new Thread(new SyncThread(objA,objB),"t1");
        Thread t2 = new Thread(new SyncThread(objB,objC),"t2");
        Thread t3 = new Thread(new SyncThread(objC,objA),"t3");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t3.start();
    }

    static class SyncThread implements Runnable{
        private Object obj1;
        private Object obj2;
        public SyncThread(Object o1, Object o2){
            this.obj1 = o1;
            this.obj2 = o2;
        }
        @Override
        public void run(){
            String name = Thread.currentThread().getName();
            synchronized (obj1){
                System.out.println(name + " acquire lock on " + obj1);
                threadSleep();
                synchronized (obj2){
                    System.out.println("After " + name + " acquire lock on " + obj2);
                    threadSleep();
                }
                System.out.println(name + " release lock on " + obj1);
                System.out.println(name + " finish execution!");
            }
        }
        void threadSleep(){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
