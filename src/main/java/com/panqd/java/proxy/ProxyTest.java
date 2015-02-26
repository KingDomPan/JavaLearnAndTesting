package com.panqd.java.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        LogHandler lh = new LogHandler();
        HelloSpeaker hs = new HelloSpeaker();
        System.out.println("hs_______" + hs);
        IHello speaker = (IHello)lh.bind(hs);
        System.out.println("return proxy______" + speaker);
        speaker.hello("panqd");
    }
}
