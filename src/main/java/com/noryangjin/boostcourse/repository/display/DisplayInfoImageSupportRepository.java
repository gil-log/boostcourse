package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfoImage;

public interface DisplayInfoImageSupportRepository {

    // displayInfoId로 displayInfoImage 출력
    public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(long displayInfoId);
}
