package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.FileInfo;
import lombok.Getter;
import lombok.Setter;

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
}
