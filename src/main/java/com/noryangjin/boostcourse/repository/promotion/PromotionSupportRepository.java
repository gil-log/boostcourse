package com.noryangjin.boostcourse.repository.promotion;

import com.noryangjin.boostcourse.domain.Promotion;

import java.util.List;

public interface PromotionSupportRepository {

    public List<Promotion> getPromotions();
}
