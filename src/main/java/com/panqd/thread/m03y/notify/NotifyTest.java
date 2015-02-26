package com.panqd.thread.m03y.notify;

/**
 * 子线程循环1次, 主线程循环2次, 重复3次
 * @author KingDom
 */
/** 共同数据的若干个方法聚集到单个类中 **/
public class NotifyTest {
    public static void main(String[] args) {
        final Busi busi = new NotifyTest.Busi();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    busi.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 3; i++) {
            busi.main(i);
        }
    }

    static class Busi {

        private boolean isSub = true;

        public synchronized void sub(int i) {
            while (!isSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 1; j <= 1; j++) {
                System.out.println("sub " + j + "--->" + i);
            }

            isSub = false;
            this.notify();
        }

        public synchronized void main(int i) {
            while (isSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 2; j++) {
                System.out.println("main " + j + "--->" + i);
            }
            isSub = true;
            this.notify();
        }
    }
}
