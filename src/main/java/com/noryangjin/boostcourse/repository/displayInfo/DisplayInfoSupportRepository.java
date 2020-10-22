package com.noryangjin.boostcourse.repository.displayInfo;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DisplayInfoSupportRepository {

    public List<DisplayInfo> listCategoryId(Long categoryId, Pageable pageable);
}
