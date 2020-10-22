package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import com.noryangjin.boostcourse.domain.FileInfo;
import com.noryangjin.boostcourse.domain.Product;
import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import com.noryangjin.boostcourse.repository.category.CategoryRepository;
import com.noryangjin.boostcourse.repository.display.DisplayInfoRepository;
import com.noryangjin.boostcourse.repository.file.FileInfoRepository;
import com.noryangjin.boostcourse.repository.product.ProductRepository;
import com.noryangjin.boostcourse.util.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Service
public class DisplayInfoServiceImpl implements DisplayInfoService{

    private static final Logger logger = LoggerFactory.getLogger(DisplayInfoServiceImpl.class);

    @Autowired
    DisplayInfoRepository displayInfoRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileInfoRepository fileInfoRepository;

    public List<DisplayInfoDTO.DisplayInfos> findDisplayInfos(long categoryId, int start){

        PageRequest pageRequest = new PageRequest(start);

        List<DisplayInfo> displayInfoList = new LinkedList<>();

        // categoryid = 0 인 전체조회 경우 고려
        if(categoryId != 0 ){
            displayInfoList = displayInfoRepository.listCategoryId(categoryId, pageRequest.of());
        } else{
            displayInfoList = displayInfoRepository.listNoCategoryId(pageRequest.of());
        }
        Iterator<DisplayInfo> displayInfoIterator = displayInfoList.iterator();

        List<DisplayInfoDTO.DisplayInfos> displayInfosList = new LinkedList<DisplayInfoDTO.DisplayInfos>();

        while(displayInfoIterator.hasNext()){
            DisplayInfoDTO.DisplayInfos displayInfos = new DisplayInfoDTO.DisplayInfos(displayInfoIterator.next());

            // displayInfoId로 product 찾기
            Product product = productRepository.findByProductId(displayInfos.getId());

            // product 관련 정보 입력
            displayInfos.setDescription(product.getDescription());
            displayInfos.setContent(product.getContent());
            displayInfos.setEvent(product.getEvent());

            // category 관련 정보 입력
            long category_id = product.getCategory_id();
            displayInfos.setCategoryid(category_id);
            displayInfos.setName(categoryRepository.findById(category_id).getName());

            // file 관련 정보 입력
            FileInfo fileInfo = fileInfoRepository.findByFileName(
                    displayInfos.getId(), "ma_"
            );
            displayInfos.setFileid(fileInfo.getId());

            displayInfosList.add(displayInfos);
            //logger.info("DTO : " +categories.getId() +", "+categories.getName());
        }

        return displayInfosList;

    }

    @Override
    public long CoutingDisplayInfo() {
        return displayInfoRepository.countingDisplayInfo();
    }
}
