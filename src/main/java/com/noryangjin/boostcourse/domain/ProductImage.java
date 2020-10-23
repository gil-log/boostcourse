package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.File;

@Getter
@Table(name="product_image")
@Entity
public class ProductImage {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 2)
    private String type;

    @Column(unique = true)
    private long product_id;

    @Column(unique = true)
    private long file_id;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="file_id", insertable = false, updatable = false)
    private FileInfo fileInfo;
}
