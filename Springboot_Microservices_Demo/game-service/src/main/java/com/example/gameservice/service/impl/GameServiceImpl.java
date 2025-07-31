package com.example.gameservice.service.impl;

import com.example.gameservice.dto.GameDTO;
import com.example.gameservice.entity.Game;
import com.example.gameservice.repo.GameRepo;
import com.example.gameservice.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author Thrimal Avishka
 * @since 12-Jul-25
 **/
@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GameDTO saveGame(GameDTO gameDTO) {
        if(Objects.isNull(gameDTO)) {
            throw new IllegalArgumentException("GameDTO cannot be null");
        }
        try {
            Game game = modelMapper.map(gameDTO, Game.class);
            Game save = gameRepo.save(game);
            if (save.getId()== null || save.getId() <= 0) {
                throw new RuntimeException("Game not saved successfully");
            }
            return modelMapper.map(save, GameDTO.class);
        }catch (Exception e) {
            throw new RuntimeException("Error saving game: " + e.getMessage(), e);
        }
    }

    @Override
    public GameDTO updateGame(GameDTO gameDTO) {
        if (Objects.isNull(gameDTO) || gameDTO.getId() == null || gameDTO.getId() <= 0) {
            throw new IllegalArgumentException("Invalid GameDTO or ID");
        }
        try {
            Game existingGame = gameRepo.findById(gameDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameDTO.getId()));
            existingGame.setName(gameDTO.getName());
            existingGame.setCategory(gameDTO.getCategory());
            existingGame.setReleaseDate(gameDTO.getReleaseDate());
            existingGame.setPrice(gameDTO.getPrice());
            existingGame.setDescription(gameDTO.getDescription());
            existingGame.setImageUrl(gameDTO.getImageUrl());
            Game updatedGame = gameRepo.save(existingGame);
            return modelMapper.map(updatedGame, GameDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error updating game: " + e.getMessage(), e);
        }
    }

    @Override
    public GameDTO findGameById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        try {
            return gameRepo.findById(id)
                    .map(game -> modelMapper.map(game, GameDTO.class))
                    .orElseThrow(() -> new RuntimeException("Game not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving game: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteGame(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        try {
            if (gameRepo.existsById(id)) {
                gameRepo.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("Game not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting game: " + e.getMessage(), e);
        }
    }

    @Override
    public List<GameDTO> findAllGames() {
        try {
            List<Game> games = gameRepo.findAll();
            if (games.isEmpty()) {
                throw new RuntimeException("No games found");
            }
            return games.stream()
                    .map(game -> modelMapper.map(game, GameDTO.class))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving games: " + e.getMessage(), e);
        }
    }
}
