package lk.ijse.orm_coursework.config;

import lk.ijse.orm_coursework.entity.Reservation;
import lk.ijse.orm_coursework.entity.Room;
import lk.ijse.orm_coursework.entity.Student;
import lk.ijse.orm_coursework.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;


    private SessionFactoryConfig() {

        Properties properties=new Properties();

        try {
            properties.load(SessionFactoryConfig.class.getResourceAsStream("/hostal.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sessionFactory = new Configuration().setProperties(properties)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Reservation.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return (null==factoryConfig)?factoryConfig=new SessionFactoryConfig()
                :factoryConfig;

    }


    public Session getSession() {

        return sessionFactory.openSession();
    }
}
