package com.panqd.thread.m04y.local;

import java.util.Random;

public class ThreadLocalTest {

    private static final ThreadLocal<Integer> tl = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + " Thread put data = " + data);
                    tl.set(data);
                    new ModuleA().getData();
                    new ModuleB().getData();
                }
            }).start();
        }
    }

    static class ModuleA {
        public void getData() {
            int data = tl.get();
            System.out.println("module A get data:" + data + "  from"
                    + Thread.currentThread().getName());
        }
    }

    static class ModuleB {
        public void getData() {
            int data = tl.get();
            System.out.println("module B get data:" + data + "  from"
                    + Thread.currentThread().getName());
        }
    }
}
