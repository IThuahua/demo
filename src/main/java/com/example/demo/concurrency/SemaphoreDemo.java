package com.example.demo.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java信号灯
 *
 * @Author zhoushenghua on 2019-06-29
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //Semaphore concurrency = new Semaphore(1,true);

        Semaphore semaphore = new Semaphore(2);
        for(int i = 0; i<6; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("线程:" + Thread.currentThread().getName() + "进入,已有" + (2-semaphore.availablePermits()) + "并发");

                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "即将离开");

                    semaphore.release();
                    System.out.println("线程" + Thread.currentThread().getName() + "已经离开,当前并发数为:" + (2-semaphore.availablePermits()));
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

    }

//    public static void main(String[] args) {
//        twoThreadPrint();
//    }

    //两个线程实现奇偶交替打印
    static AtomicInteger num = new AtomicInteger(2);
    static int topLimit = 101;  //volatile static int num = 2;
    public static void twoThreadPrint(){
        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.intValue() < topLimit){
                    //偶数判断处
                    if(num.intValue()%2 == 0 ){
                        try{
                            //此处判断必须有,不然有可能会打印出100,释放semaphore前,已为99,此时t1走到偶数判断处,然后t2执行完num为100偶数,执行下列代码
                            if(num.intValue() < topLimit){
                                semaphore.acquire();
                                System.out.println(Thread.currentThread().getName() + ":" + num.intValue());
                                //Thread.sleep(500);
                                if(num.getAndIncrement() > 0){
                                    semaphore.release();
                                }
                            }
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "first");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.intValue() < topLimit){
                    if(num.intValue()%2 == 1 ){
                        try{
                            if(num.intValue() < topLimit){
                                semaphore.acquire();
                                System.out.println(Thread.currentThread().getName() + ":" + num.intValue());
                                //Thread.sleep(500);
                                if(num.getAndIncrement() > 0){
                                    semaphore.release();
                                }
                            }
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "second");
        t1.start();
        t2.start();
    }

}
