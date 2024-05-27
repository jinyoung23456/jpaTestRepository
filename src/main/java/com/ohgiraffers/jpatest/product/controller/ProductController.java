package com.ohgiraffers.jpatest.product.controller;

import com.ohgiraffers.jpatest.common.Pagenation;
import com.ohgiraffers.jpatest.common.PagingButton;
import com.ohgiraffers.jpatest.product.dto.ProductCategoryDTO;
import com.ohgiraffers.jpatest.product.service.ProductService;
import com.ohgiraffers.jpatest.product.dto.ProductInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String findProductList(Model model, @PageableDefault Pageable pageable) {

        Page<ProductInfoDTO> productList = productService.findProductList(pageable);

        PagingButton paging = Pagenation.getPagingButtonInfo(productList);

        model.addAttribute("productList", productList);
        model.addAttribute("paging", paging);

        return "product/list";
    }

    @GetMapping("/{productCode}")
    public String findByCode(@PathVariable int productCode, Model model) {

        ProductInfoDTO resultProduct = productService.findProductByProductCode(productCode);

        model.addAttribute("product", resultProduct);

        return "product/detail";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping("/category")
    @ResponseBody
    public List<ProductCategoryDTO> findCategoryList() {

        return productService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registNewProduct(@ModelAttribute ProductInfoDTO productDTO) {

        System.out.println(productDTO.getReleaseDate());

        productService.registProduct(productDTO);

        System.out.println(productDTO.getReleaseDate());

        return "redirect:/product/list";
    }

    @GetMapping("/modify")
    public void modifyPage(){}

    @PostMapping("/modify")
    public String modifyProduct(@ModelAttribute ProductInfoDTO productInfoDTO) throws IllegalAccessException {

        System.out.println(productInfoDTO.getProductName());

        productService.modifyProduct(productInfoDTO);

        return "redirect:/product/" + productInfoDTO.getProductCode();
    }

    @GetMapping("/delete")
    public void deletePage(){}

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Integer productCode) {

        productService.deleteProduct(productCode);

        return "redirect:/product/list";
    }


}
