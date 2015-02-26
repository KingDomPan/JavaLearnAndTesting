package com.panqd.thread.m01y.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("boom");
            }
        }, 3000, 1000);//延迟3s执行, 之后每隔1s再次执行
    }
}
