package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.dto.DisplayInfoDTO;

import java.util.List;

public interface DisplayInfoService {

    // categoryId로 전시정보 출력
    public List<DisplayInfoDTO.DisplayInfos> findDisplayInfosByCategoryId(long categoryId, int start);

    // displayId로 해당 전시정보 출력
    public DisplayInfoDTO.DisplayInfos findDisplayInfosByDisplayId(long displayId);

    // 전시정보 개수 출력
    public long CoutingDisplayInfo();

    // displayInfoImages 출력
    public DisplayInfoDTO.DisplayInfoImages findDisplayInfoImagesByDisplayId(long displayId);
}
