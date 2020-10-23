package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.domain.FileInfo;
import com.noryangjin.boostcourse.domain.Product;
import com.noryangjin.boostcourse.domain.Promotion;
import com.noryangjin.boostcourse.dto.PromotionDTO;
import com.noryangjin.boostcourse.repository.category.CategoryRepository;
import com.noryangjin.boostcourse.repository.file.FileInfoRepository;
import com.noryangjin.boostcourse.repository.product.ProductRepository;
import com.noryangjin.boostcourse.repository.promotion.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(DisplayInfoServiceImpl.class);

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Override
    public List<PromotionDTO.Promotions> getPromotions() {
        List<Promotion> promotion = promotionRepository.getPromotions();

        Iterator<Promotion> iterator = promotion.iterator();

        List<PromotionDTO.Promotions> promotionsList = new LinkedList<>();

        while(iterator.hasNext()){
            PromotionDTO.Promotions promotions = new PromotionDTO.Promotions(iterator.next());

            // Product 관련 정보 입력
            Product product = productRepository.findByProductId(promotions.getProductid());
            promotions.setDescription(product.getDescription());

            // Category 관련 정보 입력
            Optional<Category> category = categoryRepository.findById(product.getCategory_id());

            logger.info("{}",category.get().getId());
            logger.info("{}",category.get().getName());

            promotions.setCategoryid(category.get().getId());
            promotions.setCategoryName(category.get().getName());

            // FileInfo 관련 정보 입력

            FileInfo fileInfo = fileInfoRepository.findByFileName(product.getId(), "ma_");
            promotions.setFileid(fileInfo.getId());

            promotionsList.add(promotions);
        }

        return promotionsList;
    }
}
