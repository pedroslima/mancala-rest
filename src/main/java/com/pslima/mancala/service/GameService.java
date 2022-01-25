package com.pslima.mancala.service;

import com.pslima.mancala.DTO.CurrentGameDTO;
import com.pslima.mancala.domain.Game;
import com.pslima.mancala.enums.Player;
import com.pslima.mancala.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BoardService boardService;

    public Game newGame() {
        List<Integer> defaultBoard = boardService.getDefaultBoard();
        Game game = new Game(defaultBoard);
        return gameRepository.save(game);
    }

    public CurrentGameDTO getCurrentBoard(long id) {
        CurrentGameDTO currentGameDTO = new CurrentGameDTO();
        Game game = gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        currentGameDTO.setBoardStatus(game.getBoard());
        currentGameDTO.setPlayer(game.getTurn());

        boardService.setCurrentBoard(game.getBoard());
        currentGameDTO.setMancalaP1(boardService.getPlayerMancala(Player.PLAYER_1));
        currentGameDTO.setMancalaP2(boardService.getPlayerMancala(Player.PLAYER_2));

        return currentGameDTO;
    }

    public void moveFrom(long boardId, Player player, int fromIdx) {
        Game game = gameRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
        boardService.setCurrentBoard(game.getBoard());

        if (game.getTurn() != player) {
            return;
        }

        boolean hasMoved = boardService.moveFrom(fromIdx, player);
        if (hasMoved) {
            Player nextTurn = this.getNextTurn(game.getTurn());
            game.setTurn(nextTurn);
            List<Integer> currentBoard = boardService.getCurrentBoard();
            game.setBoard(currentBoard);
            gameRepository.save(game);
        }
    }

    private Player getNextTurn(Player currPlayer) {
        if (currPlayer == Player.PLAYER_1) {
            return Player.PLAYER_2;
        }
        return Player.PLAYER_1;
    }
}
