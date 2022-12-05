package com.megaptera.makaogift.application;

import com.megaptera.makaogift.dtos.OrderDto;
import com.megaptera.makaogift.exceptions.InvalidUser;
import com.megaptera.makaogift.exceptions.OrderNotFound;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.OrderRepository;
import com.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrderServiceTest {
    private OrderRepository orderRepository;
    private GetOrderService getOrderService;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        getOrderService = new GetOrderService(orderRepository, productRepository);
    }

    @Test
    void getOrder() {
        Long userId = 1L;
        Long id = 1L;

        given(productRepository.findById(1L))
                .willReturn(Optional.of(Product.fake(1L)));

        given(orderRepository.findById(id))
                .willReturn(Optional.of(Order.fake()));

        OrderDto orderDto = getOrderService.getOrder(userId, id);

        assertThat(orderDto.getId()).isEqualTo(id);
    }

    @Test
    void getOrderOfOtherUser() {
        Long id = 1L;

        given(orderRepository.findById(id))
                .willReturn(Optional.of(Order.fake()));

        assertThrows(InvalidUser.class, () -> getOrderService.getOrder(Order.fake().userId() + 1, id));
    }

    @Test
    void orderNotFound() {
        Long userId = 1L;
        Long id = 1L;

        assertThrows(OrderNotFound.class,
                () -> getOrderService.getOrder(userId, id));
    }
}
