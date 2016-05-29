package com.panqd.jvm.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panqd on 3/28/16.
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
