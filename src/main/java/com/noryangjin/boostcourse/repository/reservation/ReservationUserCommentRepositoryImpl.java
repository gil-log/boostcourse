package com.noryangjin.boostcourse.repository.reservation;

import com.noryangjin.boostcourse.domain.QReservationUserComment;
import com.noryangjin.boostcourse.domain.ReservationUserComment;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ReservationUserCommentRepositoryImpl extends QuerydslRepositorySupport implements ReservationUserCommentSupportRepository {

    public ReservationUserCommentRepositoryImpl() {super(ReservationUserComment.class);}

    @Override
    public Double getAvgScoreByProductId(long productId) {
        QReservationUserComment reservationUserComment = QReservationUserComment.reservationUserComment;

        return from(reservationUserComment)
                .select(reservationUserComment.score.avg())
                .where(reservationUserComment.product_id.eq(productId))
                .fetchOne();
    }
}
