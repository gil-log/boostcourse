package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

public class CategoryDTO {

    @Getter
    public static class Categories{

        private Long id;
        private String name;

        public Categories(Category category){
            this.id = category.getId();
            this.name = category.getName();
        }

    }

}
