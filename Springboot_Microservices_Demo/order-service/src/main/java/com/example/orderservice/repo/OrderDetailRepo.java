package com.example.orderservice.repo;

import com.example.orderservice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Raigam Marketing Services
 * @developer: Thrimal Avishka
 * @since: 26-Jul-25
 **/
@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long>{
}
