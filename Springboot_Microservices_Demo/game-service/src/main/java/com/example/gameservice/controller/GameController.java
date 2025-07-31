package com.example.gameservice.controller;

import com.example.gameservice.dto.GameDTO;
import com.example.gameservice.service.GameService;
import com.example.gameservice.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka
 * @since 12-Jul-25
 **/
@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<?> saveGame(@RequestBody GameDTO gameDTO) {
        GameDTO dto = gameService.saveGame(gameDTO);
        return new ResponseEntity<>(new StandardResponse<>(201, "Success", dto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateGame(@RequestBody GameDTO gameDTO) {
        GameDTO dto = gameService.updateGame(gameDTO);
        return new ResponseEntity<>(new StandardResponse<>(200, "Success", dto), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findGameById(@PathVariable Long id) {
        GameDTO dto = gameService.findGameById(id);
        return new ResponseEntity<>(new StandardResponse<>(200, "Success", dto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        boolean isDeleted = gameService.deleteGame(id);
        return new ResponseEntity<>(new StandardResponse<>(200, "Success", isDeleted), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllGames(){
        List<GameDTO> allGames = gameService.findAllGames();
        return new ResponseEntity<>(new StandardResponse<>(200, "Success", allGames), HttpStatus.OK);
    }
}
