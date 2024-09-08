package org.example.dao;

import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;


public class UserDAO {
    private SessionFactory factory;

    public UserDAO() {
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                //.addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    //Зберігання в базі даних
    public User saveTransaction(User user){
        Session session = factory.openSession();
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

    ///////////////////////////

    // Метод для отримання всіх користувачів з бази даних
    public List<User> getAllUsers() {
        Session session = factory.openSession();
        List<User> users = null;
        try {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("from User", User.class).getResultList();  // Отримуємо всіх користувачів
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
    ///////////////////////////////
    public void updateUser(User user) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);  // Оновлюємо об'єкт у базі даних
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Якщо сталася помилка, відкочується транзакція
            }
            e.printStackTrace();
        } finally {
            session.close();  // Завжди закриваємо сесію після роботи
        }
    }
    ////////////////////////


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