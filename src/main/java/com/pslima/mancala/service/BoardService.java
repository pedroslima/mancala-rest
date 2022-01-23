package com.pslima.mancala.service;

import com.pslima.mancala.DTO.CurrentBoardDTO;
import com.pslima.mancala.domain.Board;
import com.pslima.mancala.enums.Player;
import com.pslima.mancala.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Board newBoard() {
        int[] initialBoard = new int[]{4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
        return boardRepository.save(new Board(initialBoard));
    }

    public CurrentBoardDTO getCurrentBoard(long id) {
        CurrentBoardDTO curr = new CurrentBoardDTO();
        Optional<Board> board = boardRepository.findById(id);
        curr.setBoardStatus(board.get().getPockets());
        curr.setMancalaP1(0);
        curr.setMancalaP1(0);
        curr.setPlayer(Player.PLAYER_1);
        return curr;
    }

    public Boolean moveFrom(long boardId, Player player, int index) {
        return false;
    }

//    TODO: endTurn
//    TODO: moveFrom
}
