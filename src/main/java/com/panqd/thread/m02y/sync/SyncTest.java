package com.panqd.thread.m02y.sync;

public class SyncTest {

    public static void main(String[] args) {
        final Outputer outputer = new SyncTest.Outputer();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("zhangsan");
                }
            };
        }.start();
        
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("bcd");
                }
            };
        }.start();
    }

    static class Outputer {
        public /**synchronized */ void output(String name) {
            for (int i = 0, len = name.length(); i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }

}
