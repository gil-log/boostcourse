package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfoImage;
import com.noryangjin.boostcourse.domain.QDisplayInfoImage;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class DisplayInfoImageSupportRepositoryImpl extends QuerydslRepositorySupport implements DisplayInfoImageSupportRepository {

    public DisplayInfoImageSupportRepositoryImpl() {super(DisplayInfoImage.class);}

    @Override
    public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(long displayInfoId) {
        QDisplayInfoImage displayInfoImage = QDisplayInfoImage.displayInfoImage;

        return from(displayInfoImage)
                .where(displayInfoImage.display_info_id.eq(displayInfoId))
                .fetchOne();
    }
}
