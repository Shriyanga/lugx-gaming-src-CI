package com.example.gameservice.repo;

import com.example.gameservice.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Thrimal Avishka
 * @since 12-Jul-25
 **/
@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
}
