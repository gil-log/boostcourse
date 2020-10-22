package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.dto.DisplayInfoDTO;

import java.util.List;

public interface DisplayInfoService {

    // 전시정보 출력
    public List<DisplayInfoDTO.DisplayInfos> findDisplayInfos(long categoryId, int start);

    // 전시정보 개수 출력
    public long CoutingDisplayInfo();
}
