package com.megaptera.makaogift.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.megaptera.makaogift.application.GetProductService;
import com.megaptera.makaogift.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.megaptera.makaogift.application.GetProductsService;
import com.megaptera.makaogift.dtos.ProductDto;
import com.megaptera.makaogift.dtos.ProductsDto;
import com.megaptera.makaogift.models.Product;

@RestController
@RequestMapping("products")
public class ProductController {
    private GetProductsService getProductsService;
    private GetProductService getProductService;

    public ProductController(
            GetProductsService getProductsService, GetProductService getProductService) {
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
    }

    @GetMapping
    public ProductsDto list() {
        List<ProductDto> productDtos = getProductsService.getProducts()
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return new ProductsDto(productDtos);
    }

    @GetMapping("{id}")
    public ProductDto detail(@PathVariable Long id) {
        Product product = getProductService.getProduct(id);

        return product.toDto();
    }

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFound() {
        return "Product not found";
    }
}
