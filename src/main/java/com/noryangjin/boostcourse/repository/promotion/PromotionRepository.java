package com.noryangjin.boostcourse.repository.promotion;

import com.noryangjin.boostcourse.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>, PromotionSupportRepository {
}
