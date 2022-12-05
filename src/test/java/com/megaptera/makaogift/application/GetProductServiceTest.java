package com.megaptera.makaogift.application;

import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductServiceTest {
    private GetProductService getProductService;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        getProductService = new GetProductService(productRepository);
    }

    @Test
    void getProduct() {
        given(productRepository.findById(1L))
                .willReturn(Optional.of(Product.fake(1L)));

        assertThat(getProductService.getProduct(1L).id())
                .isEqualTo(1L);
    }
}
