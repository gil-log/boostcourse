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
            this.id = displayInfo.getProduct_id();
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

    }

    @Getter
    @Setter
    public static class DisplayInfoImages{
        private long id;
        private long displayInfoId;
        private long fileId;
        private String fileName;
        private String saveFileName;
        private String contentType;
        private long deleteFlag;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;

        public DisplayInfoImages(DisplayInfoImage displayInfoImage){
            this.id = displayInfoImage.getId();
            this.displayInfoId = displayInfoImage.getDisplay_info_id();
            this.fileId = displayInfoImage.getFile_id();
        }

        public DisplayInfoImages(FileInfo fileInfo){
            this.fileId = fileInfo.getId();
            this.fileName = fileInfo.getFile_name();
            this.saveFileName = fileInfo.getSave_file_name();
            this.contentType = fileInfo.getContent_type();
            this.deleteFlag = fileInfo.getDelete_flag();
            this.createDate = fileInfo.getCreate_date();
            this.modifyDate = fileInfo.getModify_date();
        }
    }
}
