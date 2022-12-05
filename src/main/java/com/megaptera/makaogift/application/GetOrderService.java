package com.megaptera.makaogift.application;

import com.megaptera.makaogift.dtos.OrderDto;
import com.megaptera.makaogift.exceptions.InvalidUser;
import com.megaptera.makaogift.exceptions.OrderNotFound;
import com.megaptera.makaogift.exceptions.ProductNotFound;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.OrderRepository;
import com.megaptera.makaogift.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetOrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public GetOrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDto getOrder(Long userId, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound(id));

        if (order.userId() != userId) {
            throw new InvalidUser();
        }

        Product product = productRepository.findById(order.productId())
                .orElseThrow(() -> new ProductNotFound(order.productId()));

        return new OrderDto(order.id(), product.toDto(), order.count(), order.totalPrice(product.price()),
                order.to(), order.address(), order.message(), order.createdAt());
    }
}
