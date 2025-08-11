package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_items")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;
    @Column(name = "order_id", nullable = false, columnDefinition = "BIGINT")
    private Long orderId;
    @Column(name = "game_id", nullable = false, columnDefinition = "BIGINT")
    private Long gameId;
    @Column(name = "quantity", nullable = false, columnDefinition = "INT")
    private Integer quantity;
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double price;
}
