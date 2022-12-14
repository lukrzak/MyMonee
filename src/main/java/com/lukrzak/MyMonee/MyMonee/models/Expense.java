package com.lukrzak.MyMonee.MyMonee.models;

import com.lukrzak.MyMonee.MyMonee.enumerations.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    public Expense(String name, Categories category, String model, String placeOfPurchase, double price, int quantity, User user) {
        this.name = name;
        this.category = category;
        this.model = model;
        this.placeOfPurchase = placeOfPurchase;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Categories category;
    private String model;
    private String placeOfPurchase;
    private double price;
    private int quantity;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void addDateOnCreate(){
        date = new Date();
    }
}
