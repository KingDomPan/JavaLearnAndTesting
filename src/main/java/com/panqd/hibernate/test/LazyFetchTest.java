package com.panqd.hibernate.test;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.panqd.hibernate.entity.Customer;
import com.panqd.hibernate.entity.Order;
import com.panqd.hibernate.util.HibernateUtil;


public class LazyFetchTest extends TestRoot {

    public static void main(String[] args) {
        testBatchInsert();
        // testLazy4TrueFetch4Select();
        System.out.println("________________________");
        // testLazy4TrueFetchJoin();
        System.out.println("________________________");
        testCoding4FetchJoin();
    }

    public static void testBatchInsert() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            for (int i = 0; i < 25; i++) {
                Customer c = new Customer("KingDomPan");
                Order o = new Order(String.valueOf(i), c);
                session.save(o);
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

    /**
     * SELECT * FROM CUSTOMERS customer0_ WHERE customer0_.ID=?;
     * SELECT * FROM ORDERS orders0_ WHERE orders0_.CUSTOMER_ID=?;
     */
    public static void testLazy4TrueFetch4Select() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Customer c = (Customer) session.get(Customer.class, 1L);
            Set<Order> orders = c.getOrders();
            System.out.println(orders);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void testLazy4TrueBatchSize() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Iterator<Customer> it = (Iterator<Customer>) session.createQuery("from Customer c").list().iterator();
            Customer c1 = it.next();
            Customer c2 = it.next();
            Customer c3 = it.next();
            c1.getOrders().iterator(); // 集合批量初始化
            c2.getOrders().iterator();
            c3.getOrders().iterator();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    public static void testLazy4TrueFetchJoin() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Customer c = (Customer) session.get(Customer.class, 1L);
            c.getOrders(); // 立即检索发生迫切左外连接
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    public static void testCoding4FetchJoin() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("from Customer as c").list(); // 延迟检索策略
            session.createQuery("from Customer as c left join fetch c.orders where c.id=1").list(); // 显示指定用迫切左外连接
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
