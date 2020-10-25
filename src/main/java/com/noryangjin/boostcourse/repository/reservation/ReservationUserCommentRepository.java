package com.noryangjin.boostcourse.repository.reservation;

import com.noryangjin.boostcourse.domain.ReservationUserComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long>, ReservationUserCommentSupportRepository {

}

interface ReservationUserCommentSupportRepository {

    // productId에 해당하는 댓글 점수 평균 얻기
    public Double getAvgScoreByProductId(long productId);

    // resrvationUserComment 얻기
    public List<ReservationUserComment> getReservationUserCommentByProductId(long productId, Pageable pageable);

    // productId에 달린 댓글 개수 counting
    public long countReservationUserCommentByProductId(long productId);
}
