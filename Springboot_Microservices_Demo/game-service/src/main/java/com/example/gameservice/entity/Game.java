package com.example.gameservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "games")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "category", nullable = false, columnDefinition = "VARCHAR(100)")
    private String category;
    @Column(name = "release_date",  columnDefinition = "DATE")
    private Date releaseDate;
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double price;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "image_url", columnDefinition = "VARCHAR(500)")
    private String imageUrl;
}
