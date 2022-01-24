package com.pslima.mancala.domain;

import com.pslima.mancala.enums.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_game"
    )
    private Long id;
    private int[] board;
    private Player turn;

    public Game(int[] board) {
        this.board = board;
        this.turn = Player.PLAYER_1;
    }
}
