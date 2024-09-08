package org.example;

import org.example.dao.UserDAO;
import org.example.entities.User;

import javax.swing.*;
import java.time.LocalDateTime;


public class  Main {
   public static void main(String[] args) {
      UserDAO userDAO = new UserDAO();
      User user = new User(0,"SA","NA", User.Gender.FEMALE,
              LocalDateTime.now());
      //
      Integer save = userDAO.save(user);
      System.out.println(save);
      //
      User byId = userDAO.findById(save);
      System.out.println(byId);
      //
      user.setId(0);
      System.out.println(user);
      User saveMerge = userDAO.saveMerge(user);
      System.out.println(user);
      System.out.println(saveMerge);


   }
}