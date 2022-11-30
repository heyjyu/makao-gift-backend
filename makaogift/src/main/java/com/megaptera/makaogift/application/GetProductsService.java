package com.megaptera.makaogift.application;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.ProductRepository;

@Service
@Transactional
public class GetProductsService {
    private ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
