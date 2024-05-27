package com.ohgiraffers.jpatest.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCode;
    private String productName;
    private String originCost;
    private String releaseDate;
    private String discountRate;
    private String salesQuantity;
    private String stockQuantity;
    private String categoryCode;
    private String productionStatus;

    public void modifyProductName(String productName) {

        this.productName = productName;
    }

}
