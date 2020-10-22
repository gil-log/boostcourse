package com.noryangjin.boostcourse.repository.reservation;

import com.noryangjin.boostcourse.domain.ReservationInfoPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPrice, Long> {
}
