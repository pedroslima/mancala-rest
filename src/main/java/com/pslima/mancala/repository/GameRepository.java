package com.pslima.mancala.repository;

import com.pslima.mancala.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
