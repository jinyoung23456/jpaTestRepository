package com.ohgiraffers.jpatest.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCategoryDTO {

    private int categoryCode;
    private String categoryName;

}
