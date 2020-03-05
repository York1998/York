package emstest;

import com.jpj.project.common.domain.Admin;
import com.jpj.project.common.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class DaoTest {

    @Test
    public  void test1(){
        Session session= HibernateUtils.getSession();
        System.out.println(session);
    }

    @Test
    public void test2(){
        Admin admin11 = new Admin().setName("test222").setPassword("test11");
        Session session = HibernateUtils.getSession();
        HibernateUtils.beginSessionTransaction();
        session.save(admin11);
        HibernateUtils.commitAndCloseSession();
    }
}
