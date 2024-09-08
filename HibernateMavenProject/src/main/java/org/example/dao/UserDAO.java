package org.example.dao;

import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class UserDAO {
    private SessionFactory factory;

    public UserDAO() {
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                //.addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

        public Integer save(User user){
        Session session = factory.getCurrentSession();
        Integer saved = null;
            try {
                Transaction transaction = session.beginTransaction();
                saved = (Integer) session.save(user);
                transaction.commit();
            } finally {
                session.close();
            }
            return saved;
    }

    public User saveMerge(User user){
        Session session = factory.getCurrentSession();
        User saved = null;
        try {
            Transaction transaction = session.beginTransaction();
            saved = session.merge(user);
            transaction.commit();
        } finally {
            session.close();
        }
        return saved;
    }

    public User findById(Integer id){
        Session session = factory.getCurrentSession();
        User user = null;
        try{
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return user;
    }
}