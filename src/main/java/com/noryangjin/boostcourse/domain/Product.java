package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name="product")
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="category_id", unique = true)
    private Long category_id;

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition="varchar(4000)")
    private String event;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

}
