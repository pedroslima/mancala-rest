package com.pslima.mancala.domain;

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
    private @Id
    @GeneratedValue
    Long id;
    private Date created;
    @OneToOne()
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    public Match(Date created, Game game) {
        this.created = created;
        this.game = game;
    }
}
