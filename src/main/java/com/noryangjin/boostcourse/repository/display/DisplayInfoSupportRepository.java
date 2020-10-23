package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DisplayInfoSupportRepository {

    // categoryid가 주어졌을때 DisplayInfo list
    public List<DisplayInfo> listCategoryId(Long categoryId, Pageable pageable);

    // categoryid가 없을때 DisplayInfo List
    public List<DisplayInfo> listNoCategoryId(Pageable pageable);

    // displayId가 주어졌을때 DisplayInfo 출력
    public DisplayInfo getDisplayInfoByDisplayId(long displayId);

    public long findProductIdByDisplayInfoId(long displayInfo_id);

    // DisplayInfo Count
    public long countingDisplayInfo();

}
