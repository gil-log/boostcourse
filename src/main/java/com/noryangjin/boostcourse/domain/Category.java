package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Table
@Entity
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50)
    private String name;

}
