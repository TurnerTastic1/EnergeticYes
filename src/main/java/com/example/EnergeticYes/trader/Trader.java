package com.example.EnergeticYes.trader;

import jakarta.persistence.*;

@Entity
@Table
public class Trader {
    @Id
    @SequenceGenerator(
            name = "trader_sequence",
            sequenceName = "trader_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "trader_sequence"
    )
    private long id;
    private String name;
    private String email;
    private String password;

    public Trader() {
    }

    public Trader(long id,
                  String name,
                  String email,
                  String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Trader(String name,
                  String email,
                  String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password +
                '}';
    }

}
