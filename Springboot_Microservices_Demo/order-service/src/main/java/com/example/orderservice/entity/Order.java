package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Raigam Marketing Services
 * @developer: Thrimal Avishka
 * @since: 26-Jul-25
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    private Long userId;
    @Column(name = "total_price", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double totalPrice;
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50)")
    private String status;
    @CreatedDate
    @Column(name = "created_at",nullable = false, updatable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
}
