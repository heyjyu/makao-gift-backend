package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.OrderFailed;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.OrderRepository;
import com.megaptera.makaogift.repositories.ProductRepository;
import com.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        userRepository = mock(UserRepository.class);
        orderService = new OrderService(orderRepository, productRepository, userRepository);
    }

    @Test
    void orderSuccess() {
        User user = User.fake();
        Long initialAmount = user.amount();

        given(userRepository.findById(any()))
                .willReturn(Optional.of(user));

        Product product = Product.fake(1L);

        given(productRepository.findById(any()))
                .willReturn(Optional.of(product));

        Long userId = 1L;
        Long productId = 1L;
        Integer count = 1;
        Long unitPrice = 1000L;
        String to = "동길홍";
        String address = "서울시 행복구 행복동";
        String message = "행복하세요~";
        Order order = orderService.order(userId, productId, count, unitPrice, to, address, message);

        assertThat(order).isNotNull();
        assertThat(user.amount()).isEqualTo(initialAmount - product.price() * count);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void orderWithNotFoundUser() {
        given(productRepository.findById(any()))
                .willReturn(Optional.of(Product.fake(1L)));

        Long userId = 1L;
        Long productId = 1L;
        Integer count = 1;
        Long unitPrice = 1000L;
        String to = "동길홍";
        String address = "서울시 행복구 행복동";
        String message = "행복하세요~";

        assertThrows(OrderFailed.class, () -> {
            orderService.order(userId, productId, count, unitPrice, to, address, message);
        });
    }

    @Test
    void orderWithNotFoundProduct() {
        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake()));

        Long userId = 1L;
        Long productId = 1L;
        Integer count = 1;
        Long unitPrice = 1000L;
        String to = "동길홍";
        String address = "서울시 행복구 행복동";
        String message = "행복하세요~";

        assertThrows(OrderFailed.class, () -> {
            orderService.order(userId, productId, count, unitPrice, to, address, message);
        });
    }
}
