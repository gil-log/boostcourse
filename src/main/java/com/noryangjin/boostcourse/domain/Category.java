package com.noryangjin.boostcourse.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Table(name = "category")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50)
    private String name;

    @Builder
    public Category(String name){
        this.name = name;
    }



}
