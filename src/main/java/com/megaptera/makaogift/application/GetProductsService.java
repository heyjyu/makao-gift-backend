package com.megaptera.makaogift.application;

import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetProductsService {
    private ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProducts(Integer page, Integer size) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return productRepository.findAll(pageable);
    }
}
