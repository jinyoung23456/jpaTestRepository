package com.ohgiraffers.jpatest.product.service;

import com.ohgiraffers.jpatest.product.dto.ProductCategoryDTO;
import com.ohgiraffers.jpatest.product.dto.ProductInfoDTO;
import com.ohgiraffers.jpatest.product.entity.ProductCategory;
import com.ohgiraffers.jpatest.product.entity.ProductInfo;
import com.ohgiraffers.jpatest.product.repository.ProductCategoryRepository;
import com.ohgiraffers.jpatest.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Page<ProductInfoDTO> findProductList(Pageable pageable) {

        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1,
                pageable.getPageSize(),
                Sort.by("productCode")
        );

        Page<ProductInfo> productList = productRepository.findAll(pageable);

        return productList.map(productInfo -> modelMapper.map(productInfo, ProductInfoDTO.class));
    }

    public ProductInfoDTO findProductByProductCode(int productCode) {

        ProductInfo foundProduct = productRepository.findById(productCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(foundProduct, ProductInfoDTO.class);
    }

    public List<ProductCategoryDTO> findAllCategory() {

        List<ProductCategory> categoryList = productCategoryRepository.findAll();

        return categoryList.stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDTO.class))
                .toList();
    }

    @Transactional
    public void registProduct(ProductInfoDTO productDTO) {

        productDTO.setReleaseDate(productDTO.getReleaseDate().replaceAll("-", ""));

        productRepository.save(modelMapper.map(productDTO, ProductInfo.class));

    }

    @Transactional
    public void modifyProduct(ProductInfoDTO productInfoDTO) throws IllegalAccessException {

        ProductInfo foundProduct = productRepository.findById(productInfoDTO.getProductCode())
                .orElseThrow(IllegalAccessException::new);

        System.out.println(productInfoDTO.getProductCode());
        foundProduct.modifyProductName(productInfoDTO.getProductName());
        System.out.println(productInfoDTO.getProductName());
    }

    public void deleteProduct(Integer productCode) {

        productRepository.deleteById(productCode);
    }
}
