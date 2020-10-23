package com.noryangjin.boostcourse.service;

public interface ReservationService {

    // productId에 해당하는 댓글 평점 평균 얻기
    public Double getAvgScoreByProductId(long porductId);
}
