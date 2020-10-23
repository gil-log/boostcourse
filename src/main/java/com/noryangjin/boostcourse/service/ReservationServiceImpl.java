package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.ReservationUserComment;
import com.noryangjin.boostcourse.dto.ReservationDTO;
import com.noryangjin.boostcourse.repository.reservation.ReservationInfoRepository;
import com.noryangjin.boostcourse.repository.reservation.ReservationUserCommentRepository;
import com.noryangjin.boostcourse.util.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationUserCommentRepository reservationUserCommentRepository;

    @Override
    public Double getAvgScoreByProductId(long productId) {

        Double avgScore = reservationUserCommentRepository.getAvgScoreByProductId(productId);

        return avgScore == null ? 0 : avgScore;
    }

    @Override
    public List<ReservationDTO.ReservationUserComments> getReservationUserCommentsByProductId(long productId, int start) {

        PageRequest pageRequest = new PageRequest(start, 5);

        List<ReservationUserComment> reservationUserCommentList = reservationUserCommentRepository.getReservationUserCommentByProductId(
                productId, pageRequest.of()
        );

        Iterator<ReservationUserComment> iterator = reservationUserCommentList.iterator();

        List<ReservationDTO.ReservationUserComments> reservationUserComments = new LinkedList<>();

        while(iterator.hasNext()){
            ReservationDTO.ReservationUserComments reservationUserComment = new ReservationDTO.ReservationUserComments(iterator.next());

            reservationUserComments.add(reservationUserComment);
        }
        return reservationUserComments;
    }
}
