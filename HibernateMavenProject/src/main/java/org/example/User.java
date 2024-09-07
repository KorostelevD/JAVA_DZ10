package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
   public enum Gender {
      NOT_SPECIFIED, MALE, FEMALE
   }
   
   private Integer id;
   private String surname;
   private String name;
   private Gender gender;
   private LocalDateTime authorizationTime;
}
