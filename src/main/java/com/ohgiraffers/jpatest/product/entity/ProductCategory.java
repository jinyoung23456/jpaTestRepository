package com.ohgiraffers.jpatest.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category")
@Getter
@NoArgsConstructor
public class ProductCategory {

    @Id
    private int categoryCode;
    private String categoryName;

}
