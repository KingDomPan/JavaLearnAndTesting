package com.panqd.spring.aop.throww;

public class HelloSpeaker implements IHello {

    @Override
    public void hello(String name) throws Throwable {
        System.out.println(name + "___________");
        throw new Exception("人为抛出异常________-");
    }

}
