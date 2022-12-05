package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.OrderService;
import com.megaptera.makaogift.dtos.OrderCreationDto;
import com.megaptera.makaogift.dtos.OrderRequestDto;
import com.megaptera.makaogift.exceptions.OrderFailed;
import com.megaptera.makaogift.models.Order;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
}
