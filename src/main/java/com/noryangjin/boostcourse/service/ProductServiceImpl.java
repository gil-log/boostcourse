package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.FileInfo;
import com.noryangjin.boostcourse.domain.ProductImage;
import com.noryangjin.boostcourse.dto.ProductDTO;
import com.noryangjin.boostcourse.repository.file.FileInfoRepository;
import com.noryangjin.boostcourse.repository.product.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Override
    public ProductDTO.ProductImages getProductImagesByProductId(long productId) {

        ProductImage productImage = productImageRepository.findByProductIdAndType(productId, "ma");

        FileInfo fileInfo = fileInfoRepository.findById(productImage.getFile_id());

        // fileInfo 정보 입력
        ProductDTO.ProductImages productImages = new ProductDTO.ProductImages(fileInfo);

        //productImages 정보 입력
        productImages.setProductId(productId);
        productImages.setProductImageId(productImage.getId());
        productImages.setType(productImage.getType());

        return productImages;
    }
}
