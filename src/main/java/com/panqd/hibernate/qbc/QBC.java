package com.panqd.hibernate.qbc;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.panqd.hibernate.entity.Customer;
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
 * 支持在动态运行时生成查询语句
 * 使用 org.hibernate.criterion.Order 类对查询结果进行排序
 * 
 * org.hibernate.Criteria 接口
 * org.hibernate.criterion.Criterion 接口
 * org.hibernate.criterion.Restrictions 类
 * @author KingDom
 */
public class QBC extends TestRoot {
    public static void main(String[] args) {
        testBatchInsert();
        testQbc();
    }
    
    // 投影返回对象数组
    public static void testQbc() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(Customer.class);
            Criterion c1 = Restrictions.like("name", "%Dom%");
            c.add(c1);
            c.addOrder(Order.asc("name")); // 使用Order进行结果的排序
            List<?> cc = c.list();
            Iterator<?> it = cc.iterator();
            while(it.hasNext()) {
                Customer cu = (Customer)it.next();
                System.out.println(cu.getName());
                System.out.println(cu.getEmail());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
