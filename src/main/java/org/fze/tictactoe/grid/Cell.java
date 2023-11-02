package org.fze.tictactoe.grid;

import lombok.Getter;
import lombok.Setter;
import org.fze.tictactoe.player.Player;
import org.fze.tictactoe.player.Symbol;

@Getter
@Setter
public class Cell {
    private Player player;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.player = null;
    }

    public boolean isEmpty() {
        return this.player == null;
    }

    public Symbol getSymbol() {
        return this.player == null ? null : this.player.getSymbol();
    }
}
