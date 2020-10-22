package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class DisplayInfoDTO {

    @Getter
    @Setter
    public static class DisplayInfos{

        private long id;
        private long categoryid;
        private long displayinfoid;
        private String name;
        private String description;
        private String content;
        private String event;
        private String openingHours;
        private String placeName;
        private String placeLot;
        private String placeStreet;
        private String tel;
        private String homepage;
        private String email;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;
        private long fileid;

        public DisplayInfos(DisplayInfo displayInfo){
            this.id = displayInfo.getId();
            this.displayinfoid = displayInfo.getId();
            this.openingHours = displayInfo.getOpening_hours();
            this.placeName = displayInfo.getPlace_name();
            this.placeLot = displayInfo.getPlace_lot();
            this.placeStreet = displayInfo.getPlace_street();
            this.tel = displayInfo.getTel();
            this.homepage = displayInfo.getHomepage();
            this.email = displayInfo.getEmail();
            this.createDate = displayInfo.getCreate_date();
            this.modifyDate = displayInfo.getModify_date();
        }

        public DisplayInfos(Product product){
            this.description = product.getDescription();
            this.content = product.getContent();
            this.event = product.getEvent();
        }
        public DisplayInfos(Category category, FileInfo fileInfo){
            this.categoryid = category.getId();
            this.name = category.getName();
            this.fileid = fileInfo.getId();
        }


    }
}
