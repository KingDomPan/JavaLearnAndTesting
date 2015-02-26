package com.panqd.thread.m10y.fc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Runnable 不会返回结果, 并且无法抛出经过检查的异常
 * @Callable 返回结果并且可能抛出异常的任务, 实现者定义了一个不带任何参数的叫做call的方法
 * @Future 表示异步计算的结果, 提供了检查计算是否完成的方法, 以等待计算的完成, 并获取计算的结果
 *      @cancel 取消Callable的执行, 取消任务成功则返回true, 如果取消任务失败则返回false
 *          参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务, 如果设置true, 则表示可以取消正在执行过程中的任务
 *          如果任务已经完成, 则无论mayInterruptIfRunning为true还是false, 此方法肯定返回false, 即如果取消已经完成的任务会返回false;
 *          如果任务正在执行, 若mayInterruptIfRunning设置为true, 则返回true, 若mayInterruptIfRunning设置为false, 则返回false;
 *          如果任务还没有执行, 则无论mayInterruptIfRunning为true还是false, 肯定返回true;
 *      @get 用来获取执行结果, 这个方法会产生阻塞, 会一直等到任务执行完毕才返回
 *      @get(long timeout, TimeUnit unit) 用来获取执行结果, 如果在指定时间内, 还没获取到结果, 就直接返回null. 
 *      @isCanceled 任务是否被取消成功, 如果在任务正常完成前被取消成功, 则返回 true
 *      @isDone 方法表示任务是否已经完成, 若任务完成, 则返回true
 * @Executors 包含一些从其他普通形式转换成Callable类的实用方法
 */
public class FetureCallableTest {
    public static void main(String[] args) throws Exception {
        System.out.println("start main thread");  
        final ExecutorService exec = Executors.newFixedThreadPool(5);  
        Callable<String> call = new Callable<String>() {  
            public String call() throws Exception {  
                System.out.println("start new thread.");  
                Thread.sleep(1000 * 5);  
                System.out.println("end new thread.");  
                // call方法返回值
                return "some value.";  
            }  
        };  
        Future<String> task = exec.submit(call);
        Thread.sleep(1000);
        try {
            task.cancel(true); //在子线程运行时, 取消子线程任务运行, 抛出CancellationException
        } catch (Exception e) {
            System.out.println("CancellationException");
        }
        
        if(task.isCancelled()) {
            System.out.println("task is cancelled");
        }
        
        if(task.isDone()) {
            System.out.println("task is done");
        }
        // 阻塞当前线程, 即主线程, 并等待子线程结束
        // 一旦线程任务被取消, 无法再get, 否则抛出CancellationException
        task.get(); 
        exec.shutdown();  
        System.out.println("end main thread"); 
    }
}

/**Runnable和Callable都是接口
 * 1. Callable可以返回一个类型V, 而Runnable不可以
 * 2. Callable能够抛出checked exception,而Runnable不可以
 * 3. Runnable是自从java1.1就有了, 而Callable是1.5之后才加上去的
 * 4. Callable和Runnable都可以应用于executors. 而Thread类只支持Runnable
 */