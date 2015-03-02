package com.panqd.hibernate.result;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.panqd.hibernate.entity.Customer;
import com.panqd.hibernate.test.TestRoot;
import com.panqd.hibernate.util.HibernateUtil;

/**
 * 连接查询类型 HQL语法 QBC语法 内连接 inner join (join) Criteria.createAlias() 迫切内连接 innet
 * join fetch(join fetch) 不支持 隐式内连接 支持 不支持 左外连接 left outer join(left join) 不支持
 * 迫切左外连接 left outer join fetch FetchMode.JOIN 右外连接 right outer join(right join)
 * 不支持 -----------上述适用有关联关系的持久化类, 并且在映射文件中对这种关联关系做了映射 交叉连接 ClassA, ClassB 不支持
 * -----------上述适用于不存在关联关系的持久化类
 * @author KingDom
 */
public class Join extends TestRoot {

    public static void main(String[] args) {
        testBatchInsert();
        // testLeftJoinFetch();
        testLeftJoin();
    }

    /**
     * 迫切左外连接
     */
    @SuppressWarnings("unchecked")
    public static void testLeftJoinFetch() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Customer> cc = session.createQuery("from Customer c left join fetch c.orders o where c.name like :name")
                    .setString("name", "%Dom%").list();
            Set<Customer> cs = new HashSet<Customer>(cc);
            Iterator<Customer> it = cs.iterator(); // 迫切左外连接会出现持久化对象重复, 用HashSet过滤下
            while (it.hasNext()) {
                Customer c = it.next();
                System.out.println(c.getId());
                System.out.println(c.getName());
            }

            System.out.println("__________________________________");
            
            List<Customer> ccc = session.createCriteria(Customer.class)
                    .setFetchMode("orders", FetchMode.JOIN)
                    .add(Restrictions.like("name", "Dom", MatchMode.ANYWHERE))
                    .list();
            
            /**
             * 多表关联的迫切左外连接
             * session.createCriteria(ClassA.class)
                    .setFetchMode("this.b", FetchMode.JOIN)
                    .setFetchMode("this.c", FetchMode.JOIN)
                    .add(Restrictions.isNotNull("this.b"))
                    .add(Restrictions.isNotNull("this.c"))
                    .list();
             */
            Iterator<Customer> ita = ccc.iterator();
            while (ita.hasNext()) {
                Customer ca = ita.next();
                System.out.println(ca.getId());
                System.out.println(ca.getName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    /**
     * 左外连接
     */
    public static void testLeftJoin() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<?> cc = session.createQuery("from Customer c left join c.orders o where c.name like :name")
                    .setString("name", "%Dom%").list();
            Iterator<?> it = cc.iterator(); // 左外连接会出现持久化对象重复, 用HashSet过滤下
            while (it.hasNext()) {
                Object[] obj = (Object[])it.next();
                Customer c = (Customer)obj[0]; 
                c.getOrders().iterator(); //延迟加载, N+1
                System.out.println(c.getName());
            }
            System.out.println("__________________________________");
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

}
