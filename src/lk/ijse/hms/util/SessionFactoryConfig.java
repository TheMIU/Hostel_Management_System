package lk.ijse.hms.util;

import lk.ijse.hms.entity.Reservation;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;
    private SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(Reservation.class);


        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            System.out.println("file not found");
        }
        configuration.mergeProperties(properties);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (sessionFactoryConfig == null) ?
                sessionFactoryConfig = new SessionFactoryConfig() :
                sessionFactoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
