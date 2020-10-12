package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private long id;


    @Column(length = 11, unique = true)
    private int user_id;

    @Column(nullable = false)
    private String role_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
