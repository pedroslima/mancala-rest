package com.pslima.mancala.repository;

import com.pslima.mancala.domain.Game;
import com.pslima.mancala.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGameStatus(GameStatus gameStatus);
}
