package com.panqd.spring.aop.around;

public class HelloSpeaker implements IHello {

    @Override
    public void hello(String name) {
        System.out.println(name + "___________");
    }

}
