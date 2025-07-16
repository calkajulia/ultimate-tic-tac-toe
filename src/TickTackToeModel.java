import java.util.ArrayList;

public class TickTackToeModel {
    private int turn;
    private ArrayList<String> gameHistory;
    private char[][] subPanelWins;

    public TickTackToeModel() {
        turn = 0;
        gameHistory = new ArrayList<>();
        subPanelWins = new char[3][3];
    }

    public ArrayList<String> getGameHistory() {
        return gameHistory;
    }

    public void incrementTurn() {
        turn++;
    }
    public char getCurrentPlayer() {
        return turn % 2 == 0 ? '0' : 'X';
    }
    public void addMoveToGameHistory(int gridIndex, int buttonIndex) {
        gameHistory.add("Player " + getCurrentPlayer() + " moved to grid " + gridIndex + ", position " + buttonIndex);
    }

    public void updateSubPanelWins(int rowIndex, int columnIndex, char player) {
        subPanelWins[rowIndex][columnIndex] = player;
    }

    public char checkOverallWin() {
        for(int i = 0; i < 3; i++) {
            if(checkLine(subPanelWins[i][0], subPanelWins[i][1], subPanelWins[i][2]))
                return subPanelWins[i][0];
            if(checkLine(subPanelWins[0][i], subPanelWins[1][i], subPanelWins[2][i]))
                return subPanelWins[0][i];
        }
        if(checkLine(subPanelWins[0][0], subPanelWins[1][1], subPanelWins[2][2]))
            return subPanelWins[0][0];
        if(checkLine(subPanelWins[0][2], subPanelWins[1][1], subPanelWins[2][0]))
            return subPanelWins[0][2];
        return '\0';
    }
    private boolean checkLine(char c1, char c2, char c3) {
        return c1 != '\0' && c1 == c2 && c2 == c3 && c1 != 'D';
    }

    public boolean checkOverallDraw() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if (!isSubPanelWon(i, j) && !isSubPanelDraw(i, j))
                    return false;
        return true;
    }

    public boolean isSubPanelWon(int rowIndex, int columnIndex) {
        return subPanelWins[rowIndex][columnIndex] != '\0' && subPanelWins[rowIndex][columnIndex] != 'D';
    }
    public boolean isSubPanelDraw(int rowIndex, int columnIndex) {
        return subPanelWins[rowIndex][columnIndex] == 'D';
    }
}
