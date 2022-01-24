package com.pslima.mancala.DTO;

import com.pslima.mancala.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private Long gameId;
    private Long boardId;
    private Date startTime;
    private MatchStatus matchStatus;
}
