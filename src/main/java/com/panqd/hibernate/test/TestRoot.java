package com.panqd.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.panqd.hibernate.entity.Customer;
import com.panqd.hibernate.util.HibernateUtil;

public class TestRoot {
    static {
        try {
            Class.forName("com.panqd.hibernate.util.HibernateUtil");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void testBatchInsert() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            for (int i = 0; i < 25; i++) {
                Customer c = new Customer("KingDomPan");
                session.save(c);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
