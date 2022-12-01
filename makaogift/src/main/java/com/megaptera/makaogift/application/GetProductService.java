package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.ProductNotFound;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetProductService {
    private ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound(id));

        return product;
    }
}
