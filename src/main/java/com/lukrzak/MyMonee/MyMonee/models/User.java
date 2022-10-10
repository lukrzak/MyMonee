package com.lukrzak.MyMonee.MyMonee.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull private Long id;
    @NotNull private String name;
    @NotNull private String surname;
    @NotNull private double balance;

    public User(String name, String surname, double balance) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }
}
