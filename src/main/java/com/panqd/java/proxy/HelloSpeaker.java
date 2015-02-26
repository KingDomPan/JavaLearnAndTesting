package com.panqd.java.proxy;

public class HelloSpeaker implements IHello {

    @Override
    public void hello(String name) {
        System.out.println(name + "___________");
    }

}
