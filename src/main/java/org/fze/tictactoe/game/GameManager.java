package org.fze.tictactoe.game;

import org.fze.tictactoe.grid.Grid;
import org.fze.tictactoe.player.Player;
import org.fze.tictactoe.player.Symbol;

import java.util.Objects;
import java.util.Scanner;

public class GameManager {
    private final Grid grid;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public GameManager() {
        this.grid = new Grid();
        this.player1 = new Player(1, Symbol.X);
        this.player2 = new Player(2, Symbol.O);
        this.currentPlayer = this.player1;
    }

    public void play() {
        Player winner = null;
        this.grid.print();
        while (Objects.isNull(winner) && !this.grid.isFull()) {
            boolean played = false;
            while (!played) {
                played = this.playerTurn();
            }
            this.switchPlayer();
            winner = this.grid.getWinner();
        }
        if (Objects.isNull(winner)) {
            System.out.println("It's a draw!");
            return;
        }
        System.out.printf("Player %d won!%n", winner.getNumber());
    }

    private boolean playerTurn() {
        Integer row = getRow();
        Integer column = getColumn();
        if (!this.grid.isCellEmpty(row, column)) {
            System.out.println("Cell is not empty, please enter a different cell.");
            return false;
        }
        this.grid.setCellPlayer(row, column, this.currentPlayer);
        this.grid.print();
        return true;
    }

    private Integer getRow() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Player %d, please enter your move's row:%n", this.currentPlayer.getNumber());
        Integer row = null;
        while (row == null) {
            row = scanner.nextInt();
            if (row < 0 || row > 2) {
                System.out.println("Invalid row, please enter a number between 0 and 2:");
                row = null;
                continue;
            }
            if (this.grid.isRowFull(row)) {
                System.out.println("Row is full, please enter a different row:");
                row = null;
            }
        }
        return row;
    }

    private Integer getColumn() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Player %d, please enter your move's column:%n", this.currentPlayer.getNumber());
        Integer column = null;
        while (column == null) {
            column = scanner.nextInt();
            if (column < 0 || column > 2) {
                System.out.println("Invalid column, please enter a number between 0 and 2:");
                column = null;
                continue;
            }
            if (this.grid.isColumnFull(column)) {
                System.out.println("Column is full, please enter a different column:");
                column = null;
            }
        }
        return column;
    }

    private void switchPlayer() {
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }
    }
}
