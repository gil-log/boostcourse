package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = true)
    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    // cascade는 엔티티들 영속관계를 한번에 처리하지 못하기에 추가
    // user, user_role 동시조회 위해 fetch 설정 즉시로딩으로 EAGER 설정 해야 에러 안남
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="id")
    private List<UserRole> user_role;

}
