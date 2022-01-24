package com.pslima.mancala.DTO;

import com.pslima.mancala.enums.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentGameDTO {
    Player player;
    int mancalaP1;
    int mancalaP2;
    int[] boardStatus;
}
