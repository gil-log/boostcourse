package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.ReservationUserComment;
import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.dto.ReservationDTO;
import com.noryangjin.boostcourse.repository.reservation.ReservationInfoRepository;
import com.noryangjin.boostcourse.repository.reservation.ReservationUserCommentRepository;
import com.noryangjin.boostcourse.repository.user.UserRepository;
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

    @Autowired
    UserRepository userRepository;

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

            ReservationUserComment comment = iterator.next();

            // email 제외 한 정보
            ReservationDTO.ReservationUserComments reservationUserComment = new ReservationDTO.ReservationUserComments(comment);

            // email 정보
            User user = userRepository.findById(comment.getUser_id());
            reservationUserComment.setReservationEmail(user.getEmail());

            reservationUserComments.add(reservationUserComment);
        }
        return reservationUserComments;
    }

    @Override
    public long countReservationUserCommentByProductId(long productId) {
        return reservationUserCommentRepository.countReservationUserCommentByProductId(productId);
    }
}
