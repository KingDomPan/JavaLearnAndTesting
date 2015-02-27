package com.panqd.hibernate.test;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.panqd.hibernate.entity.Customer;
import com.panqd.hibernate.util.HibernateUtil;


public class BatchTest extends TestRoot {

    public static void main(String[] args) {
        testBatchInsert();
        // testBatchUpdate();
        testBatchUpdateByQuery();
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

    public static void testBatchUpdate() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        ScrollableResults sr = session.createQuery("from Customer").scroll(
                ScrollMode.FORWARD_ONLY);
        try {
            int num = 0;
            while (sr.next()) {
                Customer c = (Customer) sr.get(0);
                c.setName("panqd");
                if (++num % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public static void testBatchUpdateByQuery() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        int result = 0;
        try {
            result = session.createQuery("update Customer c set c.name=:name")
                    .setString("name", "panqd").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            System.out.println(result);
        }
    }
}
