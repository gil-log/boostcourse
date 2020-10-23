package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    // productId에 해당하는 댓글 평점 평균 얻기
    public Double getAvgScoreByProductId(long porductId);

    // productId, start(페이징 위치)로 resrvationUserComments 구하기
    public List<ReservationDTO.ReservationUserComments> getReservationUserCommentsByProductId(long productId, int start);
}
