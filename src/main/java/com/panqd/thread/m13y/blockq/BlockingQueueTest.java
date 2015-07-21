package com.panqd.thread.m13y.blockq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BlockingQueue BlockQueue如果为空, 取线程就会进去阻塞进入等待状态, 直到队列不为空才会被唤醒
 *                BlockingQueue如果为满, 放线程就会进入阻塞等待状态, 直到队列有位子了才会被唤醒
 * @常用方法 
 *  add(anObject) 如果BlockingQueue可以容纳,则返回true, 否则抛出'队列满'异常 
 *  offer(anObject) 表示如果可能的话, 如果BlockingQueue可以容纳, 则返回true, 否则返回false 
 *  put(anObject) 如果BlockQueue没有空间, 则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续 
 *  poll(time) 取走BlockingQueue里排在首位的对象, 若不能立即取出, 则可以等time参数规定的时间, 取不到时返回null 
 *  take() 取走BlockingQueue里排在首位的对象, 若BlockingQueue为空, 阻断进入等待状态直到Blocking有新的对象被加入为止
 * @实现类 ArrayBlockingQueue 规定大小的BlockingQueue, 构造函数带int参数来指明其大小. FIFO
 *      LinkedBlockingQueue 大小不定, 构造带参数, BQ有限制, 否则按照Integer.MAX_VALUE. FIFO
 *      PriorityBlockingQueue 数组实现, 容量至少为1, 默认为11,
 *      依据对象的自然排序顺序或者是构造函数的Comparator决定的顺序 SynchronousQueue 同步队列. 同步队列没有任何容,
 *      每个插入必须等待另一个线程移除, 反之亦然
 * @比较 LinkedBlockingQueue和ArrayBlockingQueue比较起来,它们背后所用的数据结构不一样,
 *     导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue,
 *     但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue
 */
public class BlockingQueueTest {
    /** 定义装苹果的篮子 */
    public static class Basket {
        private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

        public void produce() throws InterruptedException {
            this.queue.put("An apple");
        }

        public String consume() throws InterruptedException {
            return this.queue.take();
        }
        
        public int getSize() {
            return this.queue.size();
        }
    }

    public static void testBasket() {
        final Basket basket = new Basket();// 建立一个装苹果的篮子
        final long start = System.currentTimeMillis();
        class Producer implements Runnable {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("生产者准备生产苹果: "
                                + (System.currentTimeMillis() - start) + ", 当前有" + basket.getSize() + "个苹果");
                        basket.produce();
                        System.out.println("生产者生产苹果完毕: "
                                + (System.currentTimeMillis() - start) + ", 当前有" + basket.getSize() + "个苹果");
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        class Consumer implements Runnable {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("消费者准备消费苹果: " + (System.currentTimeMillis() - start) + ", 当前有" + basket.getSize() + "个苹果");
                        basket.consume();
                        System.out.println("消费者消费苹果完毕: " + (System.currentTimeMillis() - start) + ", 当前有" + basket.getSize() + "个苹果");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        ExecutorService es = Executors.newCachedThreadPool();
        Producer p = new Producer();
        Consumer c = new Consumer();
        es.submit(p);
        es.submit(c);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        es.shutdownNow();
    }
    
    public static void main(String[] args) {
        BlockingQueueTest.testBasket();
    }

}
