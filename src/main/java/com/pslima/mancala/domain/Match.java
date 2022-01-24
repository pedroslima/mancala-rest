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
    private Date created;
    private MatchStatus matchStatus;
    @OneToOne()
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    public Match(Date created, MatchStatus matchStatus, Game game) {
        this.created = created;
        this.matchStatus = matchStatus;
        this.game = game;
    }
}
