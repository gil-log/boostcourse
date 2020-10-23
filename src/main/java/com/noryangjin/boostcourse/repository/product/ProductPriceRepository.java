package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long>, ProductPriceSupportRepository {
}
