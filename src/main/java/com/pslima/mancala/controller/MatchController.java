package com.pslima.mancala.controller;

import com.pslima.mancala.DTO.CurrentBoardDTO;
import com.pslima.mancala.DTO.MoveToDTO;
import com.pslima.mancala.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    BoardService boardService;

    @PostMapping("/match/{id}")
    Boolean moveTo(@PathVariable long id, @RequestBody MoveToDTO moveFrom) {
        return boardService.moveFrom(id, moveFrom.getPlayer(), moveFrom.getIndex());
    }

    @GetMapping("/match/{id}")
    CurrentBoardDTO getCurrentBoard(@PathVariable long id) {
        return boardService.getCurrentBoard(id);
    }
}
