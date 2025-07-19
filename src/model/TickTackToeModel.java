package model;

import java.util.ArrayList;

public class TickTackToeModel {

    private int turn;
    private char[][] boards;
    private ArrayList<String> gameHistory;

    public TickTackToeModel() {
        turn = 0;
        boards = new char[3][3];
        gameHistory = new ArrayList<>();
    }

    public void incrementTurn() {
        turn++;
    }

    public char getCurrentPlayer() {
        return turn % 2 == 0 ? 'O' : 'X';
    }

    public char[][] getBoards() {
        return boards;
    }

    public void updateBoards(int rowIndex, int columnIndex, char player) {
        boards[rowIndex][columnIndex] = player;
    }

    public ArrayList<String> getGameHistory() {
        return new ArrayList<>(gameHistory);
    }

    public void addMoveToGameHistory(int gridIndex, int buttonIndex) {
        gameHistory.add(String.format("Player %c moved to grid %d, position %d", getCurrentPlayer(), gridIndex, buttonIndex));
    }
}
