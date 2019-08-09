package com.example.demo.concurrency.deadLock;

/**
 * 简单的死锁
 * @Author zhoushenghua on
 */
public class DeadLock1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLock(true), "T1");
        Thread t2 = new Thread(new DeadLock(false), "T2"); //将false改为true使t1和t2加锁顺序一致即可解决
        t1.start();
        t2.start();
    }

    static class DeadLock implements Runnable{
        private static Object obj1 = new Object();
        private static Object obj2 = new Object();
        private boolean flag;


        public DeadLock(boolean flag){
            this.flag = flag;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + "运行");
            if(flag){
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName() + "已经锁住obj1");
                    threadSleep();
                    synchronized (obj2){
                        System.out.println(Thread.currentThread().getName() + "锁住obj2");
                    }
                }
            }else{
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName() + "已经锁住obj2");
                    threadSleep();
                    synchronized (obj1){
                        System.out.println(Thread.currentThread().getName() + "锁住obj1");
                    }
                }
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
