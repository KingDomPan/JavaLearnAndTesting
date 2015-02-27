package com.panqd.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {
    
    private static Configuration cfg = null;
    private static SessionFactory sessionFactory = null;
    
    static {
        cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);
    }
    
    public synchronized static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void main(String[] args) {
        try {
            Class.forName("com.panqd.hibernate.util.HibernateUtil");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
}
