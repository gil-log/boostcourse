package com.noryangjin.boostcourse.dto;

import com.noryangjin.boostcourse.domain.ReservationUserComment;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationDTO {

    @Getter
    @Setter
    public static class ReservationUserComments{
        private long id;
        private long productId;
        private long reservationInfoId;
        private BigDecimal score;
        private String reservationEmail;
        private String comment;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;

        public ReservationUserComments(ReservationUserComment reservationUserComment){
            this.id = reservationUserComment.getId();
            this.productId = reservationUserComment.getProduct_id();
            this.reservationInfoId = reservationUserComment.getReservation_info_id();
            this.score = reservationUserComment.getScore();
            this.comment = reservationUserComment.getComment();
            this.createDate = reservationUserComment.getCreate_date();
            this.modifyDate = reservationUserComment.getModify_date();
        }
    }
}
