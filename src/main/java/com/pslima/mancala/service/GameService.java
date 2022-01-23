package com.pslima.mancala.service;

import com.pslima.mancala.DTO.GameDTO;
import com.pslima.mancala.domain.Board;
import com.pslima.mancala.enums.GameStatus;
import com.pslima.mancala.domain.Game;
import com.pslima.mancala.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BoardService boardService;

    public GameDTO newGame(){
        Board board = boardService.newBoard();
        Game game = gameRepository.save(new Game(new Date(), GameStatus.STARTED, board));
        return toGameItemDTO(game);
    }

    public List<GameDTO> listStartedGames(){
        List<Game> games = gameRepository.findByGameStatus(GameStatus.STARTED);
        List<GameDTO> gamesDTO = new ArrayList<>();
        for (Game game : games) {
            GameDTO gameDTO = toGameItemDTO(game);
            gamesDTO.add(gameDTO);
        }
        return gamesDTO;
    }

    private GameDTO toGameItemDTO(Game game){
        GameDTO dto = new GameDTO();
        dto.setGameId(game.getId());
        dto.setStartTime(game.getStartTime());
        dto.setGameStatus(game.getGameStatus());
        dto.setBoardId(game.getBoard().getId());
        return dto;
    }

}
