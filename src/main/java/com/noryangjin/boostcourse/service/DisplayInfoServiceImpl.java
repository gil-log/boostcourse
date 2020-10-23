package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.*;
import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import com.noryangjin.boostcourse.repository.category.CategoryRepository;
import com.noryangjin.boostcourse.repository.display.DisplayInfoImageRepository;
import com.noryangjin.boostcourse.repository.display.DisplayInfoRepository;
import com.noryangjin.boostcourse.repository.file.FileInfoRepository;
import com.noryangjin.boostcourse.repository.product.ProductImageRepository;
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
    DisplayInfoImageRepository displayInfoImageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Override
    public List<DisplayInfoDTO.DisplayInfos> findDisplayInfosByCategoryId(long categoryId, int start){

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
            ProductImage productImage = productImageRepository.findByProductIdAndType(product.getId(), "ma");

            displayInfos.setFileid(productImage.getFile_id());

            displayInfosList.add(displayInfos);
            //logger.info("DTO : " +categories.getId() +", "+categories.getName());
        }

        return displayInfosList;

    }

    @Override
    public DisplayInfoDTO.DisplayInfos findDisplayInfosByDisplayId(long displayId) {

        DisplayInfo displayInfo = displayInfoRepository.getDisplayInfoByDisplayId(displayId);

        DisplayInfoDTO.DisplayInfos displayInfos = new DisplayInfoDTO.DisplayInfos(displayInfo);

        //Product 정보 입력
        Product product = productRepository.findByProductId(displayInfos.getId());

        displayInfos.setDescription(product.getDescription());
        displayInfos.setContent(product.getContent());
        displayInfos.setEvent(product.getEvent());
        displayInfos.setCategoryid(product.getCategory_id());

        //Category 정보 입력
        Category category = categoryRepository.findById(displayInfos.getCategoryid());

        displayInfos.setName(category.getName());

        //file id 정보 입력
        ProductImage productImage = productImageRepository.findByProductIdAndType(product.getId(), "ma");

        displayInfos.setFileid(productImage.getFile_id());

        return displayInfos;
    }

    @Override
    public long CoutingDisplayInfo() {
        return displayInfoRepository.countingDisplayInfo();
    }

    @Override
    public DisplayInfoDTO.DisplayInfoImages findDisplayInfoImagesByDisplayId(long displayId) {

        DisplayInfoImage displayInfoImage = displayInfoImageRepository.getDisplayInfoImageByDisplayInfoId(displayId);

        FileInfo fileInfo = fileInfoRepository.findById(displayInfoImage.getFile_id());

        // fileInfo 정보 입력
        DisplayInfoDTO.DisplayInfoImages displayInfoImages = new DisplayInfoDTO.DisplayInfoImages(fileInfo);

        //displayIfnoImage 정보 입력
        displayInfoImages.setId(displayInfoImage.getId());
        displayInfoImages.setDisplayInfoId(displayInfoImage.getDisplay_info_id());

        return displayInfoImages;
    }
}
