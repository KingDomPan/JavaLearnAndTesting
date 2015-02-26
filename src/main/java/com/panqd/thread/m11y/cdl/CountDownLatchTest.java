package com.panqd.thread.m11y.cdl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务A想要往下执行, 但必须要任务B执行完毕后才可以继续往下执行. 
 * 假如任务A调用一个CountDownLatch对象的await()方法, 
 * 任务B执行完自己的任务后调用同一个CountDownLatch对象上的countDown()方法, 
 * 调用await()方法的任务A将一直阻塞等待, 直到这个CountDownLatch对象的计数值减到0为止
 * 
 * @ CountDownLatch 机制不是用来保护共享资源或者临界区
 *      它是用来同步一个或者多个执行多个任务的线程. 
 *      只能使用一次
 *      一旦计数器到达0, 任何对它的方法的调用都是无效的. 如果你想再次同步, 你必须创建新的对象
 * @ public void countDown() 
 *      递减锁存器的计数. 如果计数到达零. 则释放所有等待的线程
 *      如果当前计数大于零. 则将计数减少
 *      如果新的计数为零. 出于线程调度目的. 将重新启用所有的等待线程
 *      如果当前计数等于零. 则不发生任何操作
 * @ public boolean await(long timeout, TimeUnit unit)
 *      使当前线程在锁存器倒计数至零之前一直等待. 除非线程被中断或超出了指定的等待时间
 *      如果当前计数为零. 则此方法立刻返回 true 值
 *      如果当前计数大于零. 则出于线程调度目的. 将禁用当前线程. 且在发生以下三种情况之一前. 该线程将一直处于休眠状态
 *          由于调用 countDown() 方法. 计数到达零
 *          其他某个线程中断当前线程
 *          已超出指定的等待时间
 *          如果计数到达零. 则该方法返回 true 值
 *              如果当前线程：在进入此方法时已经设置了该线程的中断状态; 
 *              或者在等待时被中断. 则抛出 InterruptedException. 并且清除当前线程的已中断状态
 *              如果超出了指定的等待时间. 则返回值为false
 *              如果该时间小于等于零. 则此方法根本不会等待
 */
public class CountDownLatchTest {
    // 一个CountDouwnLatch实例是不能重复使用的, 也就是说它是一次性的, 锁一经被打开就不能再关闭使用了, 
    // 如果想重复使用, 请考虑使用CyclicBarrier
    public static void main(String[] args) throws Exception {
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 如果当前计数为零, 则此方法立即返回. 
                        // 等待
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + NO + " arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 每个选手到达终点时, end就减一
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        System.out.println("Game Start");
        // begin减一, 开始游戏
        begin.countDown();
        // 等待end变为0, 即所有选手到达终点
        end.await();
        System.out.println("Game Over");
        exec.shutdown(); 
    }
}
