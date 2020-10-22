package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table
@Entity
public class DisplayInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="product_id", unique = true)
    private long product_id;

    @Column(length = 400)
    private String opening_hours;

    @Column(length = 50)
    private String place_name;

    @Column(length = 100)
    private String place_lot;

    @Column(length = 100)
    private String place_street;

    @Column(length = 20)
    private String tel;

    private String homepage;

    private String email;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

}
