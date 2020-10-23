package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.repository.reservation.ReservationInfoRepository;
import com.noryangjin.boostcourse.repository.reservation.ReservationUserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationUserCommentRepository reservationUserCommentRepository;

    @Override
    public Double getAvgScoreByProductId(long productId) {

        Double avgScore = reservationUserCommentRepository.getAvgScoreByProductId(productId);

        return avgScore == null ? 0 : avgScore;
    }
}
