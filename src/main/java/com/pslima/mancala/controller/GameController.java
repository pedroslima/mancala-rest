package com.pslima.mancala.controller;

import com.pslima.mancala.DTO.CurrentBoardDTO;
import com.pslima.mancala.DTO.MoveToDTO;
import com.pslima.mancala.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/match/{id}")
    void moveTo(@PathVariable long id, @RequestBody MoveToDTO moveFrom) {
        gameService.moveFrom(id, moveFrom.getPlayer(), moveFrom.getIndex());
    }

    @GetMapping("/match/{id}")
    CurrentBoardDTO getCurrentBoard(@PathVariable long id) {
        return gameService.getCurrentBoard(id);
    }
}
