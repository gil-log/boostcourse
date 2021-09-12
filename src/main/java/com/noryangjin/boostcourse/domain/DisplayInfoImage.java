package com.noryangjin.boostcourse.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Table(name = "display_info_image")
@Entity
public class DisplayInfoImage {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="display_info_id", unique = true)
    private long display_info_id;

    @Column(unique = true)
    private long file_id;

    @ManyToOne
    @JoinColumn(name = "display_info_id", insertable = false, updatable = false)
    private DisplayInfo displayInfo;

    @ManyToOne
    @JoinColumn(name = "file_id", insertable = false, updatable = false)
    private FileInfo fileInfo;
    
}
