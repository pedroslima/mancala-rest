package com.pslima.mancala.service;

import com.pslima.mancala.DTO.MatchDTO;
import com.pslima.mancala.domain.Game;
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
    GameService gameService;

    public MatchDTO newMatch(){
        Game game = gameService.newGame();
        Match match = matchRepository.save(new Match(new Date(), MatchStatus.STARTED, game));
        return toGameItemDTO(match);
    }

    public List<MatchDTO> listStartedMatches(){
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
        dto.setStartTime(match.getCreated());
        dto.setMatchStatus(match.getMatchStatus());
        dto.setBoardId(match.getGame().getId());
        return dto;
    }

}
