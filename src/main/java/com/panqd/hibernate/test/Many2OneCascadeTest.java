package com.panqd.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.panqd.hibernate.entity.Customer;
import com.panqd.hibernate.entity.Order;
import com.panqd.hibernate.util.HibernateUtil;

public class Many2OneCascadeTest extends TestRoot {

    public static void main(String[] args) {
        // testSave();
        // testUpdate();
        // testCascade();
        // testCascadeCustomer2Order();
    }

    public static void testSave() {
        Customer c = new Customer("KingDomPan");
        HibernateUtil.getSession().save(c);
        System.out.println(c.getId() + "  " + c.getName());
    }

    public static void testUpdate() {
        Customer c = new Customer("KingDomPan");
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(c);
            c.setName("PanKingDom");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
        
        System.out.println(c.getId() + "  " + c.getName());
    }

    public static void testCascade() {
        Customer c = new Customer("KingDomPan");
        Order o1 = new Order("1", c);
        Order o2 = new Order("2", c);
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(o1);
            session.save(o2);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    public static void testCascadeCustomer2Order() {
        Customer c = new Customer("KingDomPan");
        Order o1 = new Order("1", c);
        Order o2 = new Order("2", c);
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            c.getOrders().add(o1);
            c.getOrders().add(o2);
            o1.setCustomer(c);
            o2.setCustomer(c);
            session.save(c);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    public static Customer getCustomer(Long id) {
        return (Customer) HibernateUtil.getSession().get(Customer.class, id);
    }
}
