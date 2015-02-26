package com.panqd.spring.pointcut.advisor;

public class HelloSpeaker implements IHello {

    @Override
    public void helloNewble(String name) {
        System.out.println("helloNewble_____" + name);
    }

    @Override
    public void helloMaster(String name) {
        System.out.println("helloMaster_____" + name);
    }

}
