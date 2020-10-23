package com.noryangjin.boostcourse.repository.reservation;

public interface ReservationUserCommentSupportRepository {

    // productId에 해당하는 댓글 점수 평균 얻기
    public Double getAvgScoreByProductId(long productId);
}
