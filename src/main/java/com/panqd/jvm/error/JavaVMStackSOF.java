package com.panqd.jvm.error;

/**
 * -Xss180k StackOverflowError
 * Created by panqd on 3/28/16.
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        this.stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack Length: " + oom.stackLength);
        }
    }
}
