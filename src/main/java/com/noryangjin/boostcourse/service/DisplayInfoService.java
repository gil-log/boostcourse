package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.domain.DisplayInfo;
import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import com.noryangjin.boostcourse.repository.displayInfo.DisplayInfoRepository;
import com.noryangjin.boostcourse.util.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Service
public class DisplayInfoService {

    private static final Logger logger = LoggerFactory.getLogger(DisplayInfoService.class);

    @Autowired
    DisplayInfoRepository displayInfoRepository;

    public List<DisplayInfoDTO.DisplayInfos> findDisplayInfos(long categoryId, int start){

        PageRequest pageRequest = new PageRequest(start);

        List<DisplayInfo> displayInfoList = displayInfoRepository.listCategoryId(categoryId, pageRequest.of());

        Iterator<DisplayInfo> displayInfoIterator = displayInfoList.iterator();

        List<DisplayInfoDTO.DisplayInfos> displayInfosList = new LinkedList<DisplayInfoDTO.DisplayInfos>();

        while(displayInfoIterator.hasNext()){
            DisplayInfoDTO.DisplayInfos displayInfos = new DisplayInfoDTO.DisplayInfos(displayInfoIterator.next());

            displayInfosList.add(displayInfos);
            //logger.info("DTO : " +categories.getId() +", "+categories.getName());
        }

        return displayInfosList;

    }

}
