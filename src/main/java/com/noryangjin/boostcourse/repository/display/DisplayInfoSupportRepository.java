package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DisplayInfoSupportRepository {

    public List<DisplayInfo> listCategoryId(Long categoryId, Pageable pageable);

    public long findProductIdByDisplayInfoId(long displayInfo_id);
}
