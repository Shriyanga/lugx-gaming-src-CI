package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;

/**
 * @author: Raigam Marketing Services
 * @developer: Thrimal Avishka
 * @since: 26-Jul-25
 **/
public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
}
