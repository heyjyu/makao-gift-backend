package com.megaptera.makaogift.application;

import com.megaptera.makaogift.dtos.OrdersDto;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.OrderRepository;
import com.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrdersServiceTest {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private GetOrdersService getOrdersService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        orderRepository = mock(OrderRepository.class);
        getOrdersService = new GetOrdersService(orderRepository, productRepository);
    }

    @Test
    void getOrders() {
        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake(1L)));

        given(orderRepository.findAllByUserId(any(), any()))
                .willReturn(new PageImpl<>(List.of(Order.fake())));

        Long userId = 1L;
        Integer page = 1;
        Integer size = 8;

        OrdersDto ordersDto = getOrdersService.getOrders(userId, page, size);

        assertThat(ordersDto).isNotNull();
        assertThat(ordersDto.getOrders().get(0).getProduct().getName())
                .isEqualTo("치킨");
    }
}
