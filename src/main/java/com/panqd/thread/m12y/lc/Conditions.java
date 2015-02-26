package com.panqd.thread.m12y.lc;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition可以用来实现wait、notify、notifyAll方法的线程间同步通信. wait: 进入同步代码块, 条件限制,
 * 线程调用对象监视器的wait方法, 线程进入该对象的等待池中.
 * 如果我们有100个线程, 这100条线程有可能会因为不同的条件限制而要wait, 但结果是他们都wait在同一个对象监视器上.
 * 一旦有另一个线程处理完了某种条件限制, 这种限制的解除会让这100条线程中的5条可以继续执行, 但这个线程无法通过notify去精确通知这5条线程,
 * 只能调用notifyAll通知所有线程, 95条再重新获取到对象监视器后发现不得不继续wait. 
 * @Condition
 * Condition就对这种情况进行了很好的改进 在使用同一个锁进行互斥控制的线程, 可以在不同的Condition对象上进行等待和被唤醒
 * "多路Condition"
 */

public class Conditions {
    
    /**
     * 控制下面循环10次 
     * 线程1 循环1次 
     * 线程2 循环2次 
     * 线程3 循环3次 
     * 线程4 循环4次
     */
    public static void main(String[] args) {
        
        final Task task = new Task();
        // 启动四个线程, 分别执行任务类中的4个任务! 
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    task.output1();
                }
            }
        }, "First Thread").start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    task.output2();
                }
            }
        }, "Second Thread").start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    task.output3();
                }
            }
        }, "Third Thread").start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    task.output4();
                }
            }
        }, "Forth Thread").start();
    }
    private static class Task {
        
        private int ctrlOrder = 0;
        private Lock lock = new ReentrantLock();
        // 调用锁的newCondtion方法, 得到一个Condtion对象! 
        private Condition op1Condition = lock.newCondition();
        private Condition op2Condition = lock.newCondition();
        private Condition op3Condition = lock.newCondition();
        private Condition op4Condition = lock.newCondition();
        
        public void output1() {
            lock.lock();
            try {
                // 使用Condition也会产生假醒现象! 所以此处要用while循环进行判断! 
                while (ctrlOrder % 4 != 0) {
                    // 调用Condtion对象的await方法, 进行等待
                    op1Condition.await();
                }
                // 执行相应的业务逻辑
                for (int i = 0; i < 1; i++) {
                    System.out.println(Thread.currentThread().getName()
                            + " out put " + i);
                }
                ctrlOrder++;
                // 通过调用特定的Condition的signal来唤醒在该Condition对象上等待的线程! 精确唤醒! 
                op2Condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        
        public void output2() {
            lock.lock();
            try {
                // 使用Condition也会产生假醒现象! 所以此处要用while循环进行判断! 
                while (ctrlOrder % 4 != 1) {
                    // 调用Condtion对象的await方法, 进行等待
                    op2Condition.await();
                }
                // 执行相应的业务逻辑
                for (int i = 0; i < 2; i++) {
                    System.out.println(Thread.currentThread().getName()
                            + " out put " + i);
                }
                ctrlOrder++;
                // 通过调用特定的Condition的signal来唤醒在该Condition对象上等待的线程! 精确唤醒! 
                op3Condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        
        public void output3() {
            lock.lock();
            try {
                // 使用Condition也会产生假醒现象! 所以此处要用while循环进行判断! 
                while (ctrlOrder % 4 != 2) {
                    // 调用Condtion对象的await方法, 进行等待
                    op3Condition.await();
                }
                // 执行相应的业务逻辑
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName()
                            + " out put " + i);
                }
                ctrlOrder++;
                // 通过调用特定的Condition的signal来唤醒在该Condition对象上等待的线程! 精确唤醒! 
                op4Condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        
        public void output4() {
            lock.lock();
            try {
                // 使用Condition也会产生假醒现象! 所以此处要用while循环进行判断! 
                while (ctrlOrder % 4 != 3) {
                    // 调用Condtion对象的await方法, 进行等待
                    op4Condition.await();
                }
                // 执行相应的业务逻辑
                for (int i = 0; i < 4; i++) {
                    System.out.println(Thread.currentThread().getName()
                            + " out put " + i);
                }
                ctrlOrder++;
                // 通过调用特定的Condition的signal来唤醒在该Condition对象上等待的线程! 精确唤醒! 
                op1Condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
