package com.pslima.mancala.service;

import com.pslima.mancala.DTO.CurrentBoardDTO;
import com.pslima.mancala.domain.Game;
import com.pslima.mancala.enums.Player;
import com.pslima.mancala.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public Game newGame() {
        int[] initialBoard = new int[]{4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
        return gameRepository.save(new Game(initialBoard));
    }

    public CurrentBoardDTO getCurrentBoard(long id) {
        CurrentBoardDTO curr = new CurrentBoardDTO();
        Optional<Game> board = gameRepository.findById(id);
        curr.setBoardStatus(board.get().getPockets());
        curr.setMancalaP1(0);
        curr.setMancalaP1(0);
        curr.setPlayer(Player.PLAYER_1);
        return curr;
    }

    public void moveFrom(long boardId, Player player, int fromIdx) {
        Optional<Game> board = gameRepository.findById(boardId);
        if (board.isPresent()){
            Game game1 = moveFrom(board.get(), player, fromIdx);
            gameRepository.save(game1);
        }
    }

    private Game moveFrom(Game game, Player player, int fromIdx){
        int[] pockets = game.getPockets();
        int seeds = pockets[fromIdx];
        pockets[fromIdx] = 0;
        int step = 0;
        while (seeds > 0){
            int idx = (fromIdx + 1 + step) % pockets.length;
            pockets[idx]++;
            seeds--;
            step++;
        }

        game.setPockets(pockets);
        return game;
    }

}
