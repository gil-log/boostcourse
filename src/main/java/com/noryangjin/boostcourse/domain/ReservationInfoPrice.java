package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Table(name = "reservation_info_price")
@Entity
public class ReservationInfoPrice {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private long reservation_info_id;

    @Column(unique = true)
    private long product_price_id;

    private long count;

    @ManyToOne
    @JoinColumn(name="reservation_info_id", insertable = false, updatable = false)
    private ReservationInfo reservationInfo;

    @ManyToOne
    @JoinColumn(name="product_price_id", insertable = false, updatable = false)
    private ProductPrice productPrice;
}
