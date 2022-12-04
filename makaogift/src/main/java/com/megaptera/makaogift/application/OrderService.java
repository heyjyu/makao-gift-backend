package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.OrderFailed;
import com.megaptera.makaogift.models.Order;
import com.megaptera.makaogift.models.Product;
import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.OrderRepository;
import com.megaptera.makaogift.repositories.ProductRepository;
import com.megaptera.makaogift.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Order order(Long userId, Long productId, Integer count, String to, String address, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new OrderFailed());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new OrderFailed());

        user.order(product, count);

        Order order = new Order(null, userId, productId, count, to, address, message);

        orderRepository.save(order);

        return order;
    }
}
