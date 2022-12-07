package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.GetProductService;
import com.megaptera.makaogift.application.GetProductsService;
import com.megaptera.makaogift.exceptions.ProductNotFound;
import com.megaptera.makaogift.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductsService getProductsService;

    @MockBean
    private GetProductService getProductService;

    @Test
    void list() throws Exception {
        Integer page = 1;
        Integer size = 8;
        
        Product product = mock(Product.class);
        given(getProductsService.getProducts(page, size))
                .willReturn(new PageImpl<>(List.of(product)));

        mockMvc.perform(MockMvcRequestBuilders.get("/products?page=1&size=8"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("products")
                ));
    }

    @Test
    void detail() throws Exception {
        given(getProductService.getProduct(any()))
                .willReturn(Product.fake(1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ));
    }

    @Test
    void detailNotFound() throws Exception {
        given(getProductService.getProduct(any()))
                .willThrow(ProductNotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isNotFound());
    }
}
