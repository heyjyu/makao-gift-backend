package com.megaptera.makaogift.application;

import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductsServiceTest {
    private ProductRepository productRepository;
    private GetProductsService getProductsService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        getProductsService = new GetProductsService(productRepository);
    }

    @Test
    void getProducts() {
        Product product = mock(Product.class);

        given(productRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(product)));

        Integer page = 1;
        Integer size = 8;

        assertThat(getProductsService.getProducts(page, size))
                .hasSize(1);
    }
}
