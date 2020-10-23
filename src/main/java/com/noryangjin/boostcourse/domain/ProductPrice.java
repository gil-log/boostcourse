package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Table(name = "product_price")
@Entity
public class ProductPrice {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private long product_id;

    @Column(length = 25)
    private String price_type_name;

    private long price;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private BigDecimal discount_rate;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;
}
