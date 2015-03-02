package com.panqd.hibernate.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.panqd.hibernate.entity.Customer;
import com.panqd.hibernate.test.TestRoot;
import com.panqd.hibernate.util.HibernateUtil;

/**
 * Query和Criteria都提供了用于分页的获取查询结果的方法 setFirstResult(int firstResult); 默认起始位置=0
 * setMaxResult(int maxResult); 设定一次最多检索出来的对象数目, 默认情况下都查询出所有的对象
 * @author KingDom
 */
public class Result extends TestRoot {

    public static void main(String[] args) {
        testBatchInsert();
        // Page.testPage();
        // UniqueResult.testUniqueResult();
        // ScrollResultSet.testScrollResultSet();
        // ScrollResultSet.testScrollResultSetPage();
        NamedQuery.testNamedQuery();
    }

    static class Page {
        public static void testPage() {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            try {
                List<?> cc = session
                        .createQuery(
                                "select c.name, c.id from Customer as c order by c.name desc")
                        .setFirstResult(0).setMaxResults(10).list();

                // Criteria c =
                // session.createCriteria(Customer.class).setFirstResult(0).setMaxResults(10);
                Iterator<?> it = cc.iterator();
                while (it.hasNext()) {
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

    static class UniqueResult {
        public static void testUniqueResult() {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            try {
                Long cc = (Long) session.createQuery(
                        "select count(*) from Customer as c ").uniqueResult();
                System.out.println(cc.longValue());
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                session.close();
            }
        }
    }

    // setMaxResult(1)
    // UniqueResult() 返回一个object对象
    // 显示指定oid
    static class SingleResult {

    }

    // 按主键逐个处理结果 list类似的区别 主要是查询机制不一样
    static class QueryIterate {
        // list = select * from customers where name=XZX
        // iterate = select id from customers where name=XZX 没缓存的话会进行延迟加载
        // 造成N+1的查询
        // iterate 使用场合
        // 1. Customer中存在大量的字段
        // 2. 启用了二级缓存
        // 关闭iterate对象
        // 1. 遍历完关闭
        // 2. 关闭session对象
        // 3. 通过org.hibernate.Hibernate.close(iterator)方法关闭Iterator
        public static void testQueryIterate() {

        }
    }

    // 游标实现, appcode可以使用游标来定位特定的结果集
    // Query Criteria scroll方法返回一个org.hibernate.ScrollableResults对象
    // first() boolean
    // last() boolean
    // beforeFirst() void
    // afterLast() void
    // previous() boolean
    // next() boolean
    // scroll(n) boolean 从当前位置移动n行, 正下负上
    // setRowNumber(n) boolean 使游标移动到行号为n的行, 从0开始, 最后一行为-1. python列表区间
    // get() 获得对象
    static class ScrollResultSet {
        public static void testScrollResultSet() {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            try {
                ScrollableResults sr = session.createQuery(
                        "from Customer as c ").scroll();
                sr.first();
                Object[] o = sr.get();
                Customer c = (Customer) o[0];
                System.out.println(c.getId() + "    " + c.getName());

                sr.scroll(2);
                Customer c2 = (Customer) sr.get(0); // 获取第一个字段
                System.out.println(c2.getId() + "    " + c2.getName());
                sr.close();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                session.close();
            }
        }

        final static int PAGE_SIZE = 3;
        static List<String> firstNameOfPages = new ArrayList<String>(); // 存放每一页第一个对象的名字
        static List<Customer> pageOfCustomer = new ArrayList<Customer>(); // 存放第一页的所有Customer对象

        public static void testScrollResultSetPage() {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            try {
                ScrollableResults sr = session.createQuery(
                        "select c.name, c from Customer as c ").scroll();
                if (sr.first()) {
                    do {
                        String name = sr.getString(0);
                        firstNameOfPages.add(name);
                    } while (sr.scroll(PAGE_SIZE));
                }

                sr.beforeFirst();
                
                int i = 0;
                while((PAGE_SIZE > i++) && sr.next()) {
                    pageOfCustomer.add((Customer) sr.get(1));
                }
                
                for (int j = 0; j < firstNameOfPages.size(); j++) {
                    System.out.println(firstNameOfPages.get(j) + "_______");
                }
                
                for (int j = 0; j < pageOfCustomer.size(); j++) {
                    System.out.println(pageOfCustomer.get(j).getName() + "~~~~~~~~");
                }
                sr.close();
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                tx.rollback();
            } finally {
                session.close();
            }
        }
    }
    
    static class QueryProperty {
        
        /**
         * setFlushMode 设置缓存清理方式.(AUTO COMMIT NEVER)
         * setCacheMode 设置session与第二级缓存的交互模式(NORMAL IGNORAL GET PUT REFRESH)
         * setTimeout 设置JDBC查询超时时间(单位:s)
         * setFetchSize 为JDBC驱动程序设置数据库的批量抓取数量(实际上不是全量检索, next()后达到批量值之后再去数据库取值)
         * setLockMode 设置锁定模式
         * setComment 为SQL日志设置注解
         * setReadOnly HQL的查询方式, 对结果集设置是否为只读(清理持久化对象时, 不会同步update)
         */
        public static void testQueryProperty() {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            try {
                ScrollableResults sr = session.createQuery(
                        "from Customer as c ").scroll();
                sr.first();
                Object[] o = sr.get();
                Customer c = (Customer) o[0];
                System.out.println(c.getId() + "    " + c.getName());

                sr.scroll(2);
                Customer c2 = (Customer) sr.get(0); // 获取第一个字段
                System.out.println(c2.getId() + "    " + c2.getName());
                sr.close();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                session.close();
            }
        }
    }
    
    static class NamedQuery {
        
        /** Customer.hbm.xml 下定义
          <query name="findByName" cache-mode="get" comment="" fetch-size=30 read-only=true timeout=20>
              <![CDATA[
                  from Customer c where c.name like :name
              ]]>
          </query>
        */
        
        /** Customer.hbm.xml
          <sql-query name="findByName2">
            <return alias="c" class="com.panqd.hibernate.entity.Customer"></return>
            select {c.*} from CUSTOMERS c where c.name like :name
          </sql-query>
         */
        @SuppressWarnings("unchecked")
        public static void testNamedQuery() {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            try {
                List<Customer> cc = session.getNamedQuery("findByName").setString("name", "%Dom%").list();
                Iterator<Customer> it = cc.iterator();
                while (it.hasNext()) {
                    Customer c = it.next();
                    System.out.println(c.getId());
                    System.out.println(c.getName());
                }
                
                System.out.println("__________________________________");
                List<Customer> ccc = session.getNamedQuery("findByName2").setString("name", "%Dom%").list();
                Iterator<Customer> itc = ccc.iterator();
                while (itc.hasNext()) {
                    Customer cq = itc.next();
                    System.out.println(cq.getId());
                    System.out.println(cq.getName());
                }
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                tx.rollback();
            } finally {
                session.close();
            }
        }
    }
    
    
    static class FunctionQuery {
        
        /**不仅可以调用ANSISQL函数, 还可以调用HQL函数
         * from Customer c where lower(c.name) like :name
         * select upper(c.name) from Customer c
         * from Customer c where concat(c.name, c.email) like :param
         * from Customer c where size(c.orders)>3
         * 
         * bit_length(s) 返回参数s包含的二进制位数
         * current_date(), current_time(), current_timestamp()
         * second(d), minute(d), hour(d), day(d), month(d), year(d) 解析特定的日期参数
         * cast(t as TYPE) 把参数t转换一个hibernate类型
         * index(joinedCollection) 返回集合中元素的索引
         * minelement(c), maxelement(c), minindex(c), maxindex(c), elements(c), indices(c) 返回按索引位置排序的集合中的特定元素, 或者特定索引
         * 在Dialect类中注册的其他函数, 用于扩展HQL函数库
         */
        public static void testFunctionQuery() {

        }
    }
    
    static class QueryCondition {
        
        /**
         * like MacthMode.EXACT(完全匹配), START, END, ANYWHERE
         */
        public static void testQueryCondition() {
            Restrictions.gt("age", 18);
            Restrictions.ne("age", 18);
            Restrictions.isNull("name");
            Restrictions.eq("name", "KingDomPan").ignoreCase();
            Restrictions.in("name", new String[]{"King", "Dom", "Pan"});
            Restrictions.between("age", 18, 25);
            Restrictions.not(Restrictions.between("age", 18, 25));
            Restrictions.like("name", "%Dom%");
            Restrictions.like("name", "Dom", MatchMode.START);
            Restrictions.and(Restrictions.like("name", "%Dom%"), Restrictions.gt("age", 18));
            Restrictions.isEmpty("orders");
            
        }
    }

}
