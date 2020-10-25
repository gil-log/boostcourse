package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long>, ProductPriceSupportRepository {

}

interface ProductPriceSupportRepository {

    // productId로 productPrice List 가져오기
    public List<ProductPrice> findProductPriceByProductId(long productId);
}
