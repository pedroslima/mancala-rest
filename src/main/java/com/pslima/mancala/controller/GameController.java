package com.pslima.mancala.controller;

import com.pslima.mancala.DTO.GameDTO;
import com.pslima.mancala.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/match")
    GameDTO newGame() {
        return gameService.newGame();
    }

    @GetMapping("/match")
    List<GameDTO> all() {
        return gameService.listStartedGames();
    }
}
