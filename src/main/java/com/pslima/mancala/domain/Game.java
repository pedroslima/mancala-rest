package com.pslima.mancala.domain;

import com.pslima.mancala.enums.GameStatus;
import com.pslima.mancala.enums.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name = "board", joinColumns = @JoinColumn(name = "game_id"))
    private List<Integer> board = new ArrayList<>();
    private Player turn;
    private GameStatus gameStatus;

    public Game(List<Integer> board) {
        this.board = board;
        this.turn = Player.PLAYER_1;
        this.gameStatus = GameStatus.STARTED;
    }
}
