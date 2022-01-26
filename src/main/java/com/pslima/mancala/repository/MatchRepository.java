package com.pslima.mancala.repository;

import com.pslima.mancala.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByOrderByCreatedDesc();
}
