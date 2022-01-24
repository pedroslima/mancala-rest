package com.pslima.mancala.service;

import com.pslima.mancala.DTO.CurrentGameDTO;
import com.pslima.mancala.domain.Game;
import com.pslima.mancala.enums.Player;
import com.pslima.mancala.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public Game newGame() {
        int[] defaultBoard = new BoardService().getDefaultBoard();
        return gameRepository.save(new Game(defaultBoard));
    }

    public CurrentGameDTO getCurrentBoard(long id) {
        CurrentGameDTO currentGameDTO = new CurrentGameDTO();
        Game game = gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        currentGameDTO.setBoardStatus(game.getBoard());
        currentGameDTO.setPlayer(game.getTurn());

        BoardService boardService = new BoardService(game.getBoard());
        currentGameDTO.setMancalaP1(boardService.getPlayerMancala(Player.PLAYER_1));
        currentGameDTO.setMancalaP2(boardService.getPlayerMancala(Player.PLAYER_2));

        return currentGameDTO;
    }

    public void moveFrom(long boardId, Player player, int fromIdx) {
        Game game = gameRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);

        BoardService boardService = new BoardService(game.getBoard());
        boolean hasMoved = boardService.moveFrom(fromIdx, player);
        if (hasMoved) {
            Player nextTurn = this.getNextTurn(game.getTurn());
            game.setTurn(nextTurn);
            game.setBoard(boardService.getCurrentBoard());
            gameRepository.save(game);
        }
    }

    private Player getNextTurn(Player currPlayer){
        if (currPlayer == Player.PLAYER_1){
            return Player.PLAYER_2;
        }
        return Player.PLAYER_1;
    }
}
