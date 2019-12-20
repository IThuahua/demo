package com.example.demo.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args){
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(()->"Hello").thenApply(s->s+" world").thenApply(String::toUpperCase);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");

        CompletableFuture<String> future1 =
                CompletableFuture.supplyAsync(()->"Hello").thenCompose(s->CompletableFuture.supplyAsync(()->s + " world"));

        try {
            System.out.println(future1.get()); //Hello world
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->"100");
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(()->10);
        CompletableFuture<Double> future4 = future2.thenCombine(future3,(s,i)->Double.parseDouble(s+i));
//        使用thenCombine是将future1 和future2的结果汇总，这一点跟thenCompose()不同。其中future1和future2是并行执行的。
        try {
            System.out.println(future4.get());//10010.0
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");


        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(()->"100");
        CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(()->70);
        CompletableFuture<Void> future7 = future5.thenAcceptBoth(future6,(s,i)->{
            System.out.println(s+i);
        });

        CompletableFuture.supplyAsync(()->"Hello")
                .thenApply(s->s+" world")
                .thenApply(s->s+"\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result,throwable)-> System.out.println(result));




    }

}
