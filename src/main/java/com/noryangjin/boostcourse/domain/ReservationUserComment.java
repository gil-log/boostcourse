package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@Table
@Entity
public class ReservationUserComment {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private long product_id;

    @Column(unique = true)
    private long reservation_info_id;

    @Column(unique = true)
    private long user_id;

    @Column(columnDefinition = "DECIMAL(2,1)")
    private BigDecimal score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="reservation_info_id", insertable = false, updatable = false)
    private ReservationInfo reservationInfo;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;
}
