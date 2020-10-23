package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.dto.ProductDTO;

public interface ProductService {

    public ProductDTO.ProductImages getProductImagesByProductId(long productId);
}
