package com.noryangjin.boostcourse.repository.reservation;

import com.noryangjin.boostcourse.domain.ReservationInfo;
import com.noryangjin.boostcourse.domain.ReservationUserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Long> {
}
