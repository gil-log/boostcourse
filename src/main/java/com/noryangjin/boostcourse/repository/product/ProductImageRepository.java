package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageSupportRepository {
}
