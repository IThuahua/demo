package com.example.demo.concurrency;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自定义同步器:使同一时刻只有两个线程访问，其他的都阻塞。
 * @Author zhoushenghua on
 */
public class AQSCustom {
    private final Sync sync = new Sync(2);

    public void acquire(){
        sync.acquireShared(1);
    }

    public void release(){
        sync.releaseShared(1);
    }

    //把所有操作都委派到Sync类上,需继承AQS
    static class Sync extends AbstractQueuedSynchronizer{
        Sync(int state){
            if(state < 0){
                throw new IllegalArgumentException("number must large than zero!");
            }
            setState(state);
        }

        public int tryAcquireShared(int num){
            while(true){
                //System.out.println("try acquire ...");
                int available = getState();
                int rest = available - num;
                if(rest < 0 || compareAndSetState(available, rest)){
                    return rest;
                }
            }
        }

        public boolean tryReleaseShared(int num){
            while(true){
                //System.out.println("try release ...");
                int available = getState();
                int rest = available + num;
                if(compareAndSetState(available, rest)){
                    return true;
                }
            }

        }
    }
}
