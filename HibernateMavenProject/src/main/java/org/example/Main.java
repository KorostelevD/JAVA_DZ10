package org.example;

import org.example.dao.UserDAO;
import org.example.entities.User;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;


public class  Main {
   public static void main(String[] args) {
      UserDAO userDAO = new UserDAO();
      User user1 = new User(1,"Коростельов","Дмитро", User.Gender.MALE,
              LocalDateTime.now());
      userDAO.saveTransaction(user1);
      User user2 = new User(2,"Василенко","Петро", User.Gender.MALE,
              LocalDateTime.now());
      userDAO.saveTransaction(user2);
      User user3 = new User(3,"Білозір","Оксана", User.Gender.FEMALE,
              LocalDateTime.now());
      userDAO.saveTransaction(user3);

      // Отримуємо всіх користувачів із бази даних
      List<User> users = userDAO.getAllUsers();

      // Виводимо всіх користувачів
      if (users != null && !users.isEmpty()) {
         System.out.println();
         System.out.println("Список користувачів:");
         for (User user : users) {
            System.out.println(user);
         }
      } else {
         System.out.println("Користувачів у базі немає");
      }
      System.out.println();

      //Оновлення користувача
      user1.setSurname("Петренко");
      user1.setAuthorizationTime(LocalDateTime.now());

      //Викликаємо метод для оновлення користувача
      userDAO.updateUser(user1);
      System.out.println("Користувач оновлений: " + user1);

   }
}