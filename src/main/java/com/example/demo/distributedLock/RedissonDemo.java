package com.example.demo.distributedLock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedissonDemo {

    static int fixNum = 5;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(fixNum);
        RedissonClient redisson = Redisson.create();

        ExecutorService exec = Executors.newFixedThreadPool(fixNum);
        for (int i = 0; i < fixNum; i++) {

        }

    }

    static class TestLock implements Runnable{
        private String name;

        private CountDownLatch latch;

        RedissonClient redisson;

        public TestLock(String name, CountDownLatch latch, RedissonClient redisson) {
            this.name = name;
            this.latch = latch;
            this.redisson = redisson;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            RLock lock = redisson.getLock("TestLock");

        }
    }


}
