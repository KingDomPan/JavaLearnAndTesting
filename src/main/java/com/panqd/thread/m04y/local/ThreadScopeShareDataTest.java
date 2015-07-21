package com.panqd.thread.m04y.local;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程B会覆盖线程A的数据, 无法在线程范围内进行数据共享 
 * 定义线程范围的HashMap
 * @author KingDom
 */
public class ThreadScopeShareDataTest {

    private static final Map<Thread, Integer> map = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + " Thread put data = " + data);
                    map.put(Thread.currentThread(), data);
                    new ModuleA().getData();
                    new ModuleB().getData();
                }
            }).start();
        }
    }

    static class ModuleA {
        public void getData() {
            int data = map.get(Thread.currentThread());
            System.out.println("module A get data:" + data + "  from"
                    + Thread.currentThread().getName());
        }
    }

    static class ModuleB {
        public void getData() {
            int data = map.get(Thread.currentThread());
            System.out.println("module B get data:" + data + "  from"
                    + Thread.currentThread().getName());
        }
    }
}
