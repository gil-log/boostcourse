package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageSupportRepository {

}

interface ProductImageSupportRepository {

    // ProductId, type으로 productImage 가져오기
    public ProductImage findByProductIdAndType(long product_id, String type);

}
