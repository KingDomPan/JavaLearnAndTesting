package com.panqd.thread.m12y.lc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        this.lock.lock();
        try {
            while (this.count == this.items.length)
                this.notFull.await();
            this.items[this.putptr] = x;
            if (++this.putptr == this.items.length)
                this.putptr = 0;
            ++this.count;
            this.notEmpty.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.count == 0)
                this.notEmpty.await();
            Object x = this.items[this.takeptr];
            if (++this.takeptr == this.items.length)
                this.takeptr = 0;
            --this.count;
            this.notFull.signal();
            return x;
        } finally {
            this.lock.unlock();
        }
    }
}