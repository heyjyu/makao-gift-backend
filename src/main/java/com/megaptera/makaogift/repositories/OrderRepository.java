package com.megaptera.makaogift.repositories;

import com.megaptera.makaogift.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
