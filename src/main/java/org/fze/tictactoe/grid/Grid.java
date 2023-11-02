package org.fze.tictactoe.grid;

import org.fze.tictactoe.player.Player;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Grid {
    private final List<Cell> cells;
    private final List<CellTriplet> rows;
    private final List<CellTriplet> columns;
    private final List<CellTriplet> diagonals;

    public Grid() {
        this.cells = List.of(
                new Cell(0, 0),
                new Cell(0, 1),
                new Cell(0, 2),
                new Cell(1, 0),
                new Cell(1, 1),
                new Cell(1, 2),
                new Cell(2, 0),
                new Cell(2, 1),
                new Cell(2, 2)
        );
        this.rows = List.of(
                new CellTriplet(this.cells.get(0), this.cells.get(1), this.cells.get(2)),
                new CellTriplet(this.cells.get(3), this.cells.get(4), this.cells.get(5)),
                new CellTriplet(this.cells.get(6), this.cells.get(7), this.cells.get(8))
        );
        this.columns = List.of(
                new CellTriplet(this.cells.get(0), this.cells.get(3), this.cells.get(6)),
                new CellTriplet(this.cells.get(1), this.cells.get(4), this.cells.get(7)),
                new CellTriplet(this.cells.get(2), this.cells.get(5), this.cells.get(8))
        );
        this.diagonals = List.of(
                new CellTriplet(this.cells.get(0), this.cells.get(4), this.cells.get(8)),
                new CellTriplet(this.cells.get(2), this.cells.get(4), this.cells.get(6))
        );
    }

    public Cell getCell(int row, int column) {
        return this.cells.stream()
                .filter(cell -> cell.getRow() == row && cell.getColumn() == column)
                .findFirst()
                .orElse(null);
    }

    public Player getWinner() {
        List<CellTriplet> fullTriplets = Stream.of(
                this.getFullRows(),
                this.getFullColumns(),
                this.getFullDiagonals()
        ).flatMap(List::stream).toList();
        for (CellTriplet triplet : fullTriplets) {
            Player winner = triplet.getWinner();
            if (Objects.nonNull(winner)) {
                return winner;
            }
        }
        return null;
    }

    public boolean isRowFull(int row) {
        return this.rows.get(row).isFull();
    }

    public boolean isColumnFull(int column) {
        return this.columns.get(column).isFull();
    }

    public boolean isFull() {
        return this.cells.stream().noneMatch(Cell::isEmpty);
    }

    public boolean isCellEmpty(int row, int column) {
        return this.getCell(row, column).isEmpty();
    }

    public void setCellPlayer(int row, int column, Player player) {
        Cell cell = this.getCell(row, column);
        if (!cell.isEmpty()) {
            throw new IllegalArgumentException("Cell is not empty");
        }
        cell.setPlayer(player);
    }

    public void print() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                Cell cell = this.getCell(i, j);
                if (Objects.isNull(cell.getPlayer())) {
                    System.out.print("  | ");
                } else {
                    System.out.printf("%s | ", cell.getSymbol());
                }
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private List<CellTriplet> getFullRows() {
        return this.rows.stream()
                .filter(CellTriplet::isFull)
                .toList();
    }

    private List<CellTriplet> getFullColumns() {
        return this.columns.stream()
                .filter(CellTriplet::isFull)
                .toList();
    }

    private List<CellTriplet> getFullDiagonals() {
        return this.diagonals.stream()
                .filter(CellTriplet::isFull)
                .toList();
    }
}
