package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.GetOrderService;
import com.megaptera.makaogift.application.GetOrdersService;
import com.megaptera.makaogift.application.OrderService;
import com.megaptera.makaogift.dtos.OrderCreationDto;
import com.megaptera.makaogift.dtos.OrderDto;
import com.megaptera.makaogift.dtos.OrderRequestDto;
import com.megaptera.makaogift.dtos.OrdersDto;
import com.megaptera.makaogift.exceptions.InvalidUser;
import com.megaptera.makaogift.exceptions.OrderFailed;
import com.megaptera.makaogift.models.Order;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {
    private OrderService orderService;
    private GetOrdersService getOrdersService;
    private GetOrderService getOrderService;

    public OrderController(
            OrderService orderService, GetOrdersService getOrdersService, GetOrderService getOrderService) {
        this.orderService = orderService;
        this.getOrdersService = getOrdersService;
        this.getOrderService = getOrderService;
    }

    @GetMapping
    public OrdersDto list(
            @RequestAttribute Long userId
    ) {
        return getOrdersService.getOrders(userId);
    }

    @GetMapping("{id}")
    public OrderDto detail(
            @RequestAttribute Long userId, @PathVariable Long id
    ) {
        return getOrderService.getOrder(userId, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreationDto order(
            @RequestAttribute Long userId, @Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.order(
                userId,
                orderRequestDto.getProductId(),
                orderRequestDto.getCount(),
                orderRequestDto.getTo(),
                orderRequestDto.getAddress(),
                orderRequestDto.getMessage());

        return order.toCreationDto();
    }

    @ExceptionHandler(OrderFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String orderFailed() {
        return "Order failed!";
    }

    @ExceptionHandler(InvalidUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidUser() {
        return "Invalid user!";
    }
}
