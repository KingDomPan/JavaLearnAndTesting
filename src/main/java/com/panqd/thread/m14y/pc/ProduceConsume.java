package com.panqd.thread.m14y.pc;

/**
 * Created by panqd on 3/14/16.
 */
public class ProduceConsume {

    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Producer p = new Producer(ss);
        Consume s = new Consume(ss);
        new Thread(p).start();
        new Thread(s).start();
    }
}


class SteamBread {
    private int id;

    public SteamBread(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SteamBread: " + id;
    }
}

/**
 * 线程同步类, 使用该对象的wait和notifyAll方法来使调用线程进入等待或者唤醒状态
 */
class SyncStack {
    private int index = 0;
    private SteamBread[] stb = new SteamBread[6];

    public synchronized void push(SteamBread sb) {
        while (this.index == stb.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stb[this.index++] = sb;
        this.notifyAll();
    }

    public synchronized SteamBread pop() {
        while (this.index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        this.index--;
        return stb[this.index];
    }
}

class Producer implements Runnable {

    private SyncStack ss;

    public Producer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            SteamBread stb = new SteamBread(i);
            ss.push(stb);
            System.out.println("生产了" + stb);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consume implements Runnable {
    private SyncStack ss;

    public Consume(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            SteamBread stb = ss.pop();
            System.out.println("消费了" + stb);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}