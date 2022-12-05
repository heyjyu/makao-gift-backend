package com.megaptera.makaogift.application;

import com.megaptera.makaogift.dtos.OrderDto;
import com.megaptera.makaogift.dtos.OrdersDto;
import com.megaptera.makaogift.exceptions.ProductNotFound;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.repositories.OrderRepository;
import com.megaptera.makaogift.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetOrdersService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public GetOrdersService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrdersDto getOrders(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        List<OrderDto> orderDtos = orders.stream()
                .map(order -> {
                    Product product = productRepository.findById(order.productId())
                            .orElseThrow(() -> new ProductNotFound(order.productId()));

                    return new OrderDto(order.id(), product.toDto(), order.count(), order.totalPrice(product.price()),
                            order.to(), order.address(), order.message(), order.createdAt());
                })
                .collect(Collectors.toList());

        return new OrdersDto(orderDtos);
    }
}
