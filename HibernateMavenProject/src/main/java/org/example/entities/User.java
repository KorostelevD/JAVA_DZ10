package org.example.entities;

import jakarta.persistence.*; //мпортує анотації та класи, необхідні для роботи з JPA (Java Persistence API), наприклад, @Entity, @Id, @GeneratedValue, @Table, та інші.
import lombok.AllArgsConstructor; //Імпортує анотації з бібліотеки Lombok, яка автоматично генерує стандартні методи, такі як getter, setter, toString, equals, hashCode, а також конструктори.
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//анотації
@Data //Анотація з Lombok, яка автоматично генерує getter, setter, toString, equals, і hashCode методи для всіх полів класу.
@NoArgsConstructor //Анотація з Lombok, яка генерує конструктор без аргументів.
@AllArgsConstructor //Анотація з Lombok, яка генерує конструктор з усіма полями класу.
//
@Entity //Анотація JPA, яка вказує, що цей клас є сутністю, яка відображає таблицю в базі даних.
@Table(name = "users") //Анотація JPA, яка вказує, що цей клас відображає таблицю з ім'ям users у базі даних.

public class User {
   public enum Gender {
      NOT_SPECIFIED, MALE, FEMALE
   }

   @Id // Анотація JPA, яка вказує, що це поле є первинним ключем таблиці.
   @GeneratedValue(strategy = GenerationType.IDENTITY) //Вказує, що значення для первинного ключа буде автоматично генеруватись базою даних за допомогою стратегії IDENTITY.
   private Integer id;
   private String surname;
   private String name;
   private Gender gender;
   private LocalDateTime authorizationTime;
}
