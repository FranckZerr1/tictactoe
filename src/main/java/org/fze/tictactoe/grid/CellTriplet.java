package org.fze.tictactoe.grid;

import lombok.NonNull;
import org.fze.tictactoe.player.Player;

import java.util.List;

public class CellTriplet {
    private final List<Cell> cells;

    public CellTriplet(Cell cell1, Cell cell2, Cell cell3) {
        this.cells = List.of(cell1, cell2, cell3);
    }

    public Cell getCell(int index) throws IndexOutOfBoundsException {
        return this.cells.get(index);
    }

    public Player getWinner() {
        Player player = this.cells.get(0).getPlayer();
        if (player == null) {
            return null;
        }
        for (Cell cell : this.cells) {
            if (cell.getPlayer() != player) {
                return null;
            }
        }
        return player;
    }

    public boolean isFull() {
        return this.cells.stream().noneMatch(Cell::isEmpty);
    }
}
