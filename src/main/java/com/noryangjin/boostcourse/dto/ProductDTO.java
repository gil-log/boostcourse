package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.FileInfo;
import com.noryangjin.boostcourse.domain.ProductPrice;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {

    @Getter
    @Setter
    public static class ProductImages{
        private long productId;
        private long productImageId;
        private String Type;
        private long fileInfoId;
        private String fileName;
        private String saveFileName;
        private String contentType;
        private long deleteFlag;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;

        public ProductImages(FileInfo fileInfo){
            this.fileInfoId = fileInfo.getId();
            this.fileName = fileInfo.getFile_name();
            this.saveFileName = fileInfo.getSave_file_name();
            this.contentType = fileInfo.getContent_type();
            this.deleteFlag = fileInfo.getDelete_flag();
            this.createDate = fileInfo.getCreate_date();
            this.modifyDate = fileInfo.getModify_date();
        }

        public ProductImages(){}
    }

    @Getter
    @Setter
    public static class ProductPrices{
        private long id;
        private long productId;
        private String priceTypeName;
        private long price;
        private BigDecimal discountRate;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;

        public ProductPrices(ProductPrice productPrice){
            this.id = productPrice.getId();
            this.productId = productPrice.getProduct_id();
            this.priceTypeName = productPrice.getPrice_type_name();
            this.price = productPrice.getPrice();
            this.discountRate = productPrice.getDiscount_rate();
            this.createDate = productPrice.getCreate_date();
            this.modifyDate = productPrice.getModify_date();
        }
    }
}
