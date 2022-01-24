package com.pslima.mancala.controller;

import com.pslima.mancala.DTO.MatchDTO;
import com.pslima.mancala.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("/match")
    MatchDTO newGame() {
        return matchService.newMatch();
    }

    @GetMapping("/match")
    List<MatchDTO> list() {
        return matchService.listStartedGames();
    }
}
