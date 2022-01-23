package com.pslima.mancala.repository;

import com.pslima.mancala.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
