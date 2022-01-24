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

    public void moveFrom(long boardId, Player player, int fromIdx) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isPresent()){
            Board board1 = moveFrom(board.get(), player, fromIdx);
            boardRepository.save(board1);
        }
    }

    private Board moveFrom(Board board,Player player, int fromIdx){
        int[] pockets = board.getPockets();
        int seeds = pockets[fromIdx];
        pockets[fromIdx] = 0;
        int step = 0;
        while (seeds > 0){
            int idx = (fromIdx + 1 + step) % pockets.length;
            pockets[idx]++;
            seeds--;
            step++;
        }

        board.setPockets(pockets);
        return board;
    }

}
