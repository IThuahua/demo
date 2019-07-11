package com.example.demo.concurrency.condition;

import com.example.demo.ioc.C;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟实现简单的有界队列,队列为空时,获取操作阻塞,直到队列中有新元素,队列已满时,插入操作阻塞直到队列有空位置
 * @Author zhoushenghua on
 */
public class QueueByCondition<O> {
    private Object[] elements;
    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private int length = 0, addIndex = 0, removeIndex = 0;

    public QueueByCondition(int size){
        elements = new Object[size];
    }


    public void add(O object) throws InterruptedException{
        lock.lock();
        try{
            while(length == elements.length){
                System.out.println("队列已满,等待加入...");
                notFull.await();
            }
            elements[addIndex] = object;
            if(addIndex++ == elements.length){
                addIndex = 0;
            }
            length++;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public O remove() throws InterruptedException{
        lock.lock();
        try{
            while (0 == length){
                System.out.println("队列为空,等待...");
                notEmpty.await();
            }
            Object element = elements[removeIndex];
            if(removeIndex++ == elements.length){
                removeIndex = 0;
            }
            length--;
            notFull.signal();
            return (O) element;
        }finally {
            lock.unlock();
        }
    }
}
