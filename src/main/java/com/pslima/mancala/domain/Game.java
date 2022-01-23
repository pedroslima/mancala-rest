package com.pslima.mancala.domain;

import com.pslima.mancala.enums.GameStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game {
    private @Id @GeneratedValue Long id;
    private Date startTime;
    private GameStatus gameStatus;
    @OneToOne()
    @JoinColumn(name="board_id", referencedColumnName = "id")
    private Board board;

    public Game(Date startTime, GameStatus gameStatus, Board board) {
        this.startTime = startTime;
        this.gameStatus = gameStatus;
        this.board = board;
    }
}
