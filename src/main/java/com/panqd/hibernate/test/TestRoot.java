package com.panqd.hibernate.test;

public class TestRoot {
    static {
        try {
            Class.forName("com.panqd.hibernate.util.HibernateUtil");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
