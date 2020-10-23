package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.Promotion;
import lombok.Getter;
import lombok.Setter;

public class PromotionDTO {

    @Getter
    @Setter
    public static class Promotions{
        private long id;
        private long productid;
        private long categoryid;
        private String categoryName;
        private String description;
        private long fileid;

        public Promotions(Promotion promotion){
            this.id = promotion.getId();
            this.productid = promotion.getProduct_id();
        }

    }
}
