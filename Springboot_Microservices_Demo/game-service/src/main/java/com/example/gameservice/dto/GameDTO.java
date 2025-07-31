package com.example.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Thrimal Avishka
 * @since 12-Jul-25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private String name;
    private String category;
    private Date releaseDate;
    private Double price;
    private String description;
    private String imageUrl;
}
