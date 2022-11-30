package com.megaptera.makaogift.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megaptera.makaogift.application.GetProductsService;
import com.megaptera.makaogift.dtos.ProductDto;
import com.megaptera.makaogift.dtos.ProductsDto;
import com.megaptera.makaogift.models.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    private GetProductsService getProductsService;

    public ProductController(GetProductsService getProductsService) {
        this.getProductsService = getProductsService;
    }

    @GetMapping
    public ProductsDto list() {
        List<ProductDto> productDtos = getProductsService.getProducts()
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }
}
