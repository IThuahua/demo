package com.example.demo.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhoushenghua on
 */
public class AQSCustomDemo {

    public static void main(String[] args) {
        AQSCustom aqsCustom = new AQSCustom();

        for(int i=1; i<=5; i++){
            new Thread(new ThreadHandler("task:" + i, aqsCustom)).start();
        }

    }

    static class ThreadHandler implements Runnable{
        private String name;
        private AQSCustom aqsCustom;

        ThreadHandler(String name, AQSCustom aqsCustom){
            this.name = name;
            this.aqsCustom = aqsCustom;
        }

        @Override
        public void run(){
            try{
                aqsCustom.acquire();
                System.out.println(name + " 开始执行了");
                long cost = new Random().nextInt(10000);
                Thread.sleep(cost);
                System.out.println(name + " 执行完了,用时:" + cost);
                aqsCustom.release();
            }catch (InterruptedException e){

            }
        }

    }



}
