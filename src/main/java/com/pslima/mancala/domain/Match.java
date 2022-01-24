package com.pslima.mancala.domain;

import com.pslima.mancala.enums.MatchStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Match {
    private @Id @GeneratedValue Long id;
    private Date startTime;
    private MatchStatus matchStatus;
    @OneToOne()
    @JoinColumn(name="board_id", referencedColumnName = "id")
    private Board board;

    public Match(Date startTime, MatchStatus matchStatus, Board board) {
        this.startTime = startTime;
        this.matchStatus = matchStatus;
        this.board = board;
    }
}
