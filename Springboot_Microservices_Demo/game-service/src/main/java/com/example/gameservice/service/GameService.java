package com.example.gameservice.service;

import com.example.gameservice.dto.GameDTO;

import java.util.List;

/**
 * @author Thrimal Avishka
 * @since 12-Jul-25
 **/
public interface GameService {
    GameDTO saveGame(GameDTO gameDTO);
    GameDTO updateGame(GameDTO gameDTO);
    GameDTO findGameById(Long id);
    boolean deleteGame(Long id);
    List<GameDTO> findAllGames();
}
