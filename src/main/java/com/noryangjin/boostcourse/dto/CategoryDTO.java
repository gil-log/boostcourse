package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.Category;
import lombok.*;

public class CategoryDTO {

    @Getter
    public static class Categories{

        private Long id;
        private String name;
        private Long count;

        public Categories(Category category){
            this.id = category.getId();
            this.name = category.getName();
        }

        public void Counting(long count){
            this.count = count;
        }


    }

}
