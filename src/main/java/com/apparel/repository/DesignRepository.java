package com.apparel.repository;

import com.apparel.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface DesignRepository extends JpaRepository<Design,Long> {

    List<Design> findByBrand(String brand);
    List<Design> findByMaterial(String material);
    List<Design> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);

}