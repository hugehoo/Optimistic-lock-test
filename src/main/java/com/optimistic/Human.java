package com.optimistic;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "Human")
@Data
@NoArgsConstructor
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idx")
    private Long idx;


    @Column(name = "name")
    private String name;
    @Column(name = "money")
    private Integer money;

    @Version
    @Column(name = "version")
    private Integer version;

    public Human(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    public int decreaseMoney(int money) {
        if (this.money - money < 0) {
            throw new IllegalArgumentException("돈이 부족해");
        }
        this.money -= money;
        return this.money;
    }

}
