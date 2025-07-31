package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.OrderDetailDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderDetail;
import com.example.orderservice.repo.OrderDetailRepo;
import com.example.orderservice.repo.OrderRepo;
import com.example.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Raigam Marketing Services
 * @developer: Thrimal Avishka
 * @since: 26-Jul-25
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Transactional
    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        if (Objects.isNull(orderDTO)) {
            throw new RuntimeException("Order cannot be null");
        }
        if (orderDTO.getOrderDetailDTOList() == null || orderDTO.getOrderDetailDTOList().isEmpty()) {
            throw new RuntimeException("Order must have at least one order detail");
        }

        Order order = modelMapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepo.save(order);

        if (savedOrder.getId() == null || savedOrder.getId() < 1) {
            throw new RuntimeException("Failed to save order");
        }

        List<OrderDetail> detailEntities = orderDTO.getOrderDetailDTOList().stream()
                .map(dto -> {
                    OrderDetail detail = modelMapper.map(dto, OrderDetail.class);
                    detail.setOrderId(savedOrder.getId());
                    return detail;
                })
                .collect(Collectors.toList());

        List<OrderDetail> savedDetails = orderDetailRepo.saveAll(detailEntities);

        OrderDTO responseDTO = modelMapper.map(savedOrder, OrderDTO.class);
        List<OrderDetailDTO> detailDTOs = savedDetails.stream()
                .map(detail -> modelMapper.map(detail, OrderDetailDTO.class))
                .collect(Collectors.toList());

        responseDTO.setOrderDetailDTOList(detailDTOs);
        return responseDTO;
    }

}
