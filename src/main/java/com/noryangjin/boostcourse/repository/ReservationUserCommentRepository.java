package com.noryangjin.boostcourse.repository;

import com.noryangjin.boostcourse.domain.ReservationUserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long> {
}
