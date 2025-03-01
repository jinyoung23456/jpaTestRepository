package com.ohgiraffers.jpatest.product.repository;

import com.ohgiraffers.jpatest.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findAll();
}
