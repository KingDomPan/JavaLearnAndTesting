package com.panqd.activemq.spring;

import org.springframework.context.SmartLifecycle;

public class SmartLifecycleBean implements SmartLifecycle {

    @Override
    public void start() {
        System.out.println("SmartLifecycleBean_____" + "start");
    }

    @Override
    public void stop() {
        System.out.println("SmartLifecycleBean_____" + "stop");
    }

    @Override
    public boolean isRunning() {
        System.out.println("SmartLifecycleBean_____" + "isRunning");
        return true;
    }

    @Override
    public int getPhase() {
        System.out.println("SmartLifecycleBean_____" + "getPhase");
        return 0;
    }

    @Override
    public boolean isAutoStartup() {
        System.out.println("SmartLifecycleBean_____" + "isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("SmartLifecycleBean_____" + "stop_____" + "Runnable");
    }

}
