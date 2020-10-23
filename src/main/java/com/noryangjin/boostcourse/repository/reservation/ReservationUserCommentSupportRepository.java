package com.noryangjin.boostcourse.repository.reservation;

import com.noryangjin.boostcourse.domain.ReservationUserComment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationUserCommentSupportRepository {

    // productId에 해당하는 댓글 점수 평균 얻기
    public Double getAvgScoreByProductId(long productId);

    // resrvationUserComment 얻기
    public List<ReservationUserComment> getReservationUserCommentByProductId(long productId, Pageable pageable);

    // productId에 달린 댓글 개수 counting
    public long countReservationUserCommentByProductId(long productId);
}
