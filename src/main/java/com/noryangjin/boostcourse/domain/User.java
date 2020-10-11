package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(nullable = true)
    private LocalDateTime create_date;

    private LocalDateTime modify_date;

}
