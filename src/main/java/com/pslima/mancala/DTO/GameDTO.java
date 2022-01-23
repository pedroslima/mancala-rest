package com.pslima.mancala.DTO;

import com.pslima.mancala.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long gameId;
    private Long boardId;
    private Date startTime;
    private GameStatus gameStatus;
}
