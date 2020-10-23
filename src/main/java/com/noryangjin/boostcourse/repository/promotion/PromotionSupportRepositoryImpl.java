package com.noryangjin.boostcourse.repository.promotion;

import com.noryangjin.boostcourse.domain.Promotion;
import com.noryangjin.boostcourse.domain.QPromotion;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class PromotionSupportRepositoryImpl extends QuerydslRepositorySupport implements PromotionSupportRepository{

    public PromotionSupportRepositoryImpl(){super(Promotion.class);}

    @Override
    public List<Promotion> getPromotions() {
        QPromotion promotion = QPromotion.promotion;

        return from(promotion)
                .fetch();
    }
}
