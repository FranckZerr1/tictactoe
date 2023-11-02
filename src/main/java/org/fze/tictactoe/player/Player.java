package org.fze.tictactoe.player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private int number;
    private Symbol symbol;
}
