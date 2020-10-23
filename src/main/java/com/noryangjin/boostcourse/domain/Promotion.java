package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Table(name = "promotion")
@Entity
public class Promotion {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private long product_id;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
