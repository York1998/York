package com.jpj.project.common.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    //特性:线程绑定
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();
    //静态代码块,只执行一次!
    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * 获取session ,同一线程获取相同
     * @return
     */
    public  static Session getSession(){
        Session session = threadLocal.get();
        if(session ==null){//第一次获取
            session = sessionFactory.openSession();
            threadLocal.set(session);//存储
        }
        return session;
    }

    /**
     * 开启Session 事务
     */
    public  static void beginSessionTransaction(){
        Session session = threadLocal.get();
        if(session!=null){
            session.beginTransaction();
        }
    }

    /**
     * 关闭和提交
     */
    public  static void commitAndCloseSession(){
        Session session = threadLocal.get();
        if(session!=null){
            if(!session.getTransaction().wasCommitted()){
                session.getTransaction().commit();//提交
            }
        }
        closeSession();
    }

    /**
     * 只关闭session
     */
    public  static void closeSession(){
        Session session = threadLocal.get();
        if(session!=null){
            session.close();
            threadLocal.set(null);//避免下次拿到尸体
        }
    }


    public static void main(String[] args) {
        //测试线程相关的代码不能用 junit
        System.out.println("main:"+HibernateUtils.getSession().hashCode());
        System.out.println("main:"+HibernateUtils.getSession().hashCode());
        new Thread(){
            @Override
            public void run() {
                System.out.println("other:"+HibernateUtils.getSession().hashCode());
                System.out.println("other:"+HibernateUtils.getSession().hashCode());
            }
        }.start();
    }
}
