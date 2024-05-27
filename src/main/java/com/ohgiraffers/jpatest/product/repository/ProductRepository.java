package com.ohgiraffers.jpatest.product.repository;

import com.ohgiraffers.jpatest.product.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductInfo, Integer> {
    Page<ProductInfo> findAll(Pageable pageable);
}
