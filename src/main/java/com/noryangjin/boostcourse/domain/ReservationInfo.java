package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Table
@Entity
public class ReservationInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private long product_id;

    @Column(unique = true)
    private long display_info_id;

    @Column(unique = true)
    private long user_id;

    private LocalDateTime reservation_date;

    private long cancel_flag;

    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "display_info_id", insertable = false, updatable = false)
    private DisplayInfo displayInfo;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}
