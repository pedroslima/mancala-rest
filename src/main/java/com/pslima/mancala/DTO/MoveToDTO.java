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
public class MoveToDTO {
    Player player;
    int index;
}
