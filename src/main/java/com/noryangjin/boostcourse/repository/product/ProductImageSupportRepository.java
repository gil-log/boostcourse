package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductImage;

public interface ProductImageSupportRepository {

    // ProductId, type으로 productImage 가져오기
    public ProductImage findByProductIdAndType(long product_id, String type);

}
