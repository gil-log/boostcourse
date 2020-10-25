package com.noryangjin.boostcourse.repository.product;

import com.noryangjin.boostcourse.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductSupportRepository {

}

interface ProductSupportRepository {

    public Product findByProductId(long product_id);
}