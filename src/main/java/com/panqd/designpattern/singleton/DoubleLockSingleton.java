package com.panqd.designpattern.singleton;

/**
 * 多线程模式下的双重锁定单例模式
 * @author KingDomPan
 */
public class DoubleLockSingleton {

    private static DoubleLockSingleton instance = null;
    private static final Object lock = new Object();

    private DoubleLockSingleton() {
    }

    public static DoubleLockSingleton getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DoubleLockSingleton();
                }
            }
        }
        return instance;
    }

}
