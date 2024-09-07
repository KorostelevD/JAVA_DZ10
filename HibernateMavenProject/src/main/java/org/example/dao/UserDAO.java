package org.example.dao;

import org.example.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class UserDAO {
    private SessionFactory factory;

    public UserDAO() {
       this.factory = new Configuration().configure("hibernate.cfg.xml")
               //.addAnnotatedClass(User.class)
               .buildSessionFactory();
    }
}