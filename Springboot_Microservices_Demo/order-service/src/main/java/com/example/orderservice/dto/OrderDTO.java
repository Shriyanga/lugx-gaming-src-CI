package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Raigam Marketing Services
 * @developer: Thrimal Avishka
 * @since: 26-Jul-25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private String status;
    private List<OrderDetailDTO> orderDetailDTOList;
}
