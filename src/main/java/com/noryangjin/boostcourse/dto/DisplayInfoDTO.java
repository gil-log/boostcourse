package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.*;
import lombok.Getter;

import java.time.LocalDateTime;


public class DisplayInfoDTO {

    @Getter
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

        public DisplayInfos(Category category, DisplayInfo displayInfo, FileInfo fileInfo, Product product){
            this.id = displayInfo.getId();
            this.categoryid = category.getId();
            this.displayinfoid = displayInfo.getId();
            this.name = category.getName();
            this.description = product.getDescription();
            this.content = product.getContent();
            this.event = product.getEvent();
            this.openingHours = displayInfo.getOpening_hours();
            this.placeName = displayInfo.getPlace_name();
            this.placeLot = displayInfo.getPlace_lot();
            this.placeStreet = displayInfo.getPlace_street();
            this.tel = displayInfo.getTel();
            this.homepage = displayInfo.getHomepage();
            this.email = displayInfo.getEmail();
            this.createDate = displayInfo.getCreate_date();
            this.modifyDate = displayInfo.getModify_date();
            this.fileid = fileInfo.getId();
        }


    }
}
