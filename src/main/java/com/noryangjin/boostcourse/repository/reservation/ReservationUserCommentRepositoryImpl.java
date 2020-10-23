package com.noryangjin.boostcourse.repository.reservation;

import com.noryangjin.boostcourse.domain.QReservationUserComment;
import com.noryangjin.boostcourse.domain.ReservationUserComment;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<ReservationUserComment> getReservationUserCommentByProductId(long productId, Pageable pageable) {
        QReservationUserComment reservationUserComment = QReservationUserComment.reservationUserComment;

        final JPQLQuery<ReservationUserComment> query;

        query = from(reservationUserComment)
                .where(reservationUserComment.product_id.eq(productId))
        .orderBy(reservationUserComment.id.desc());

        List<ReservationUserComment> list = getQuerydsl().applyPagination(pageable, query).fetch();

        return list;
    }

    @Override
    public long countReservationUserCommentByProductId(long productId) {
        QReservationUserComment reservationUserComment = QReservationUserComment.reservationUserComment;

        return from(reservationUserComment)
                .where(reservationUserComment.product_id.eq(productId))
                .fetchCount();
    }
}
