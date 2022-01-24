package com.pslima.mancala.service;

import com.pslima.mancala.DTO.MatchDTO;
import com.pslima.mancala.domain.Board;
import com.pslima.mancala.enums.MatchStatus;
import com.pslima.mancala.domain.Match;
import com.pslima.mancala.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    BoardService boardService;

    public MatchDTO newMatch(){
        Board board = boardService.newBoard();
        Match match = matchRepository.save(new Match(new Date(), MatchStatus.STARTED, board));
        return toGameItemDTO(match);
    }

    public List<MatchDTO> listStartedGames(){
        List<Match> matches = matchRepository.findByMatchStatus(MatchStatus.STARTED);
        List<MatchDTO> gamesDTO = new ArrayList<>();
        for (Match match : matches) {
            MatchDTO matchDTO = toGameItemDTO(match);
            gamesDTO.add(matchDTO);
        }
        return gamesDTO;
    }

    private MatchDTO toGameItemDTO(Match match){
        MatchDTO dto = new MatchDTO();
        dto.setGameId(match.getId());
        dto.setStartTime(match.getStartTime());
        dto.setMatchStatus(match.getMatchStatus());
        dto.setBoardId(match.getBoard().getId());
        return dto;
    }

}
