package lk.ijse.lms.util;


import lk.ijse.lms.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Issue.class)
                .addAnnotatedClass(Return.class)
                .addAnnotatedClass(Catogery.class)
                .addAnnotatedClass(Login.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration() :
                factoryConfiguration;
    }

    public Session operSession() {
        return sessionFactory.openSession();
    }
}
