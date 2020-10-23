package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.ProductPrice;
import com.noryangjin.boostcourse.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    // productId로 ProductImages 가져오기
    public ProductDTO.ProductImages getProductImagesByProductId(long productId);

    // productId로 productPrice List 가져오기
    public List<ProductDTO.ProductPrices> findProductPriceByProductId(long productId);
}
