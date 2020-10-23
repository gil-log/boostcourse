package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.FileInfo;
import com.noryangjin.boostcourse.domain.ProductImage;
import com.noryangjin.boostcourse.domain.ProductPrice;
import com.noryangjin.boostcourse.dto.ProductDTO;
import com.noryangjin.boostcourse.repository.file.FileInfoRepository;
import com.noryangjin.boostcourse.repository.product.ProductImageRepository;
import com.noryangjin.boostcourse.repository.product.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductPriceRepository productPriceRepository;

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

    @Override
    public List<ProductDTO.ProductPrices> findProductPriceByProductId(long productId) {
        List<ProductPrice> productPriceList = productPriceRepository.findProductPriceByProductId(productId);

        Iterator<ProductPrice> iterator = productPriceList.iterator();

        List<ProductDTO.ProductPrices> productPrices = new LinkedList<>();

        while(iterator.hasNext()){
            ProductDTO.ProductPrices productPrice = new ProductDTO.ProductPrices(iterator.next());
            productPrices.add(productPrice);
        }

        return productPrices;
    }
}
