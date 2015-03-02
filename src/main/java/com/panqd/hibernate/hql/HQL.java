package com.panqd.hibernate.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.panqd.hibernate.test.TestRoot;
import com.panqd.hibernate.util.HibernateUtil;

/**
 * 1. 在查询条件中设定各种查询条件
 * 2. 支持投影查询
 * 3. 支持分页查询
 * 4. 支持连接查询
 * 5. 支持分组查询
 * 6. 提供内聚函数的支持
 * 7. 能够调用用户定义的SQL函数或者标准SQL函数
 * 8. 支持子查询
 * 9. 支持动态参数绑定
 * @author KingDom
 */

/**
 * 支持多态查询, from Customer会查询出Customer的子类
 * 采用order by进行字段的排序
 * @author KingDom
 */
public class HQL extends TestRoot {
    public static void main(String[] args) {
        testBatchInsert();
        testHql();
    }
    
    // 投影返回对象数组
    public static void testHql() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<?> cc = session.createQuery("select c.name, c.id from Customer as c order by c.name desc").list();
            Iterator<?> it = cc.iterator();
            while(it.hasNext()) {
                Object[] c = (Object[]) it.next();
                System.out.println(c[0]);
                System.out.println(c[1]);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
