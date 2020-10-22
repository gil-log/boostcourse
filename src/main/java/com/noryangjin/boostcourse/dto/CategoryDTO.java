package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.Category;
import lombok.*;
import org.springframework.stereotype.Service;

public class CategoryDTO {

    @Getter
    @Setter
    public static class Categories{

        private Long id;
        private String name;
        private Long count;

        public Categories(Category category){
            this.id = category.getId();
            this.name = category.getName();
        }
    }
}
