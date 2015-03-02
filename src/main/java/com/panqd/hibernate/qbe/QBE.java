package com.panqd.hibernate.qbe;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

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
 * 1. Qbc的子功能, 允许先创建一个对象样板, 检索出所有和这样板相同的对象
 * 2. 使用场景不是很大, 一个适合的使用场景是在查询窗口让用户输入一系列的查询条件, 返回匹配的对象
 * 3. 只支持 = like 运算符
 * @author KingDom
 */
public class QBE extends TestRoot {
    public static void main(String[] args) {
        testBatchInsert();
        testQbe();
    }
    
    // 投影返回对象数组
    public static void testQbe() {
        Customer example = new Customer();
        example.setName("KingDomPan");
        
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria c = session.createCriteria(Customer.class);
            Criterion c1 = Example.create(example); // 默认把属性不为null的值作为查询条件
            c.add(c1);
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
