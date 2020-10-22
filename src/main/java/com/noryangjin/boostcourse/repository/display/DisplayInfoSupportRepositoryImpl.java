package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import com.noryangjin.boostcourse.domain.QCategory;
import com.noryangjin.boostcourse.domain.QDisplayInfo;
import com.noryangjin.boostcourse.domain.QProduct;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class DisplayInfoSupportRepositoryImpl extends QuerydslRepositorySupport implements DisplayInfoSupportRepository{

    public DisplayInfoSupportRepositoryImpl() {super(DisplayInfo.class);}

    @Override
    public List<DisplayInfo> listCategoryId(Long categoryId, Pageable pageable) {
        final QProduct product = QProduct.product;
        final QDisplayInfo displayInfo = QDisplayInfo.displayInfo;

        final JPQLQuery<DisplayInfo> query;

        query = from(displayInfo)
                .where(displayInfo.product_id.in
                        (
                                (from(product)
                                        .select(product.id)
                                        .where(product.category_id.eq(categoryId)))
                        )
                );

        List<DisplayInfo> list = getQuerydsl().applyPagination(pageable, query).fetch();

        return list;
    }

    @Override
    public List<DisplayInfo> listNoCategoryId(Pageable pageable) {
        final QProduct product = QProduct.product;
        final QDisplayInfo displayInfo = QDisplayInfo.displayInfo;

        final JPQLQuery<DisplayInfo> query;

        query = from(displayInfo)
                ;

        List<DisplayInfo> list = getQuerydsl().applyPagination(pageable, query).fetch();

        return list;
    }

    @Override
    public long findProductIdByDisplayInfoId(long displayInfo_id) {
        final QDisplayInfo displayInfo = QDisplayInfo.displayInfo;
        return from(displayInfo)
                .select(displayInfo.product_id)
                .where(displayInfo.id.eq(displayInfo_id))
                .fetchOne();
    }

    @Override
    public long countingDisplayInfo() {
        QDisplayInfo displayInfo = QDisplayInfo.displayInfo;

        return from(displayInfo)
                .fetchCount();
    }
}
