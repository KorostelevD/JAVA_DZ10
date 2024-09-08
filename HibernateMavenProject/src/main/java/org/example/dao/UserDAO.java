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
    /////////////////////////////  ОНОВЛЕННЯ  ////////////////////////
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
    //////////////////////// ВИДАЛЕННЯ ///////////////////

    public void deleteUserById(int userId) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);  // Отримуємо користувача за ID
            if (user != null) {
                session.remove(user);  // Видаляємо користувача
                transaction.commit();
            } else {
                System.out.println("User with id " + userId + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Якщо сталася помилка, відкочуємо транзакцію
            }
            e.printStackTrace();
        } finally {
            session.close();  // Закриваємо сесію після роботи
        }
    }

}