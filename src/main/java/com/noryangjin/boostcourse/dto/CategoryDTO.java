package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

@Component
@Data
public class CategoryDTO {

    private long id;
    private String name;

}
