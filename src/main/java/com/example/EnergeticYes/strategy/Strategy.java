package com.example.EnergeticYes.strategy;

import jakarta.persistence.*;

@Entity
@Table
public class Strategy {
    @Id
    @SequenceGenerator(
            name = "strategy_sequence",
            sequenceName = "strategy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "strategy_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private String type;

    public Strategy() {
    }

    public Strategy(Long id,
                    String name,
                    String description,
                    String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Strategy(String name,
                    String description,
                    String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
