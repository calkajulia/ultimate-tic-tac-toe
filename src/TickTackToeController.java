import javax.swing.*;

public class TickTackToeController {

    private int activeBoardId;

    private TickTackToeModel model;
    private TickTackToeView view;

    public TickTackToeController(TickTackToeModel model, TickTackToeView view) {
        activeBoardId = -1;

        this.model = model;
        this.view = view;

        initializeBoard();
    }

    private void initializeBoard() {
        for(int i = 0; i < 9; i++) {
            SubBoard subBoard = new SubBoard();
            addFieldListeners(subBoard);
            view.addSubBoard(subBoard);
        }
        view.refresh();
    }

    private void addFieldListeners(SubBoard subBoard) {
        for(Field field : subBoard.fields) {
            field.addActionListener(e -> handleFieldClick(field, subBoard));
        }
    }

    private void handleFieldClick(Field field, SubBoard subBoard) {
        char currentPlayer = model.getCurrentPlayer();
        updateField(field, currentPlayer);
        updateSubBoard(subBoard, field, currentPlayer);
        model.incrementTurn();
        updateTurnDisplay();
        updateGameHistory(subBoard, field);
        handleResult(subBoard);
        if(!checkGameEnd()) {
            activeBoardId = field.getId() % 9;
            updateBoards();
        }
    }

    private void updateField(Field field, char player) {
        ImageIcon playerIcon = (player == 'O') ? Customization.IMAGE_O : Customization.IMAGE_X;
        field.setIcon(playerIcon);
        field.setEnabled(false);
    }

    private void updateSubBoard(SubBoard subBoard, Field field, char player) {
        int row = (field.getId() % 9) / 3;
        int column = (field.getId() % 9) % 3;
        subBoard.updateBoard(row, column, player);
    }

    private void updateTurnDisplay() {
        char nextPlayer = model.getCurrentPlayer();
        view.updateTurnLabel(nextPlayer + "'s turn");
    }

    private void updateGameHistory(SubBoard subBoard, Field field) {
        model.addMoveToGameHistory(subBoard.getId(), field.getId() % 9);
        view.updateGameHistory(model.getGameHistory());
    }

    private void handleResult(SubBoard subBoard) {
        char winner = checkWinner(subBoard.getBoard());
        boolean isDraw = isBoardDraw(subBoard.getBoard());

        if(winner != '\0') {
            int row = subBoard.getId() / 3;
            int column = subBoard.getId() % 3;
            model.updateBoards(row, column, winner);
            subBoard.setStatus(SubBoard.Status.COMPLETED);
            subBoard.displayWinner(winner);
        } else if(isDraw) {
            int row = subBoard.getId() / 3;
            int column = subBoard.getId() % 3;
            model.updateBoards(row, column, 'D');
            subBoard.setStatus(SubBoard.Status.COMPLETED);
            subBoard.displayWinner(' ');
        }
    }

    private boolean checkGameEnd() {
        char overallWinner = checkWinner(model.getBoards());
        boolean isOverallDraw = isBoardDraw(model.getBoards());

        if(overallWinner != '\0') {
            JOptionPane.showMessageDialog(view, "Player " + overallWinner + " won!");
            disableAllPanels();
            return true;
        } else if(isOverallDraw) {
            JOptionPane.showMessageDialog(view, "It's a draw!");
            disableAllPanels();
            return true;
        }
        return false;
    }

    private void disableAllPanels() {
        for(int i = 0; i < 9; i++) {
            view.setSubBoardEnabled(i, false);
        }
        view.refresh();
    }

    private boolean isBoardDraw(char[][] board) {
        return isBoardFull(board) && checkWinner(board) == '\0';
    }

    private boolean isBoardFull(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private char checkWinner(char[][] board) {
        // rows and columns
        for(int i = 0; i < 3; i++) {
            if(checkLine(board[i][0], board[i][1], board[i][2])) {
                return board[i][0];
            }
            if(checkLine(board[0][i], board[1][i], board[2][i])) {
                return board[0][i];
            }
        }
        // diagonals
        if(checkLine(board[0][0], board[1][1], board[2][2])) {
            return board[0][0];
        }
        if(checkLine(board[0][2], board[1][1], board[2][0])) {
            return board[0][2];
        }
        return '\0';
    }

    private boolean checkLine(char c1, char c2, char c3) {
        return c1 != '\0' && c1 == c2 && c2 == c3 && c1 != 'D';
    }

    private void updateBoards() {
        boolean isActiveBoardCompleted = isSubBoardCompleted(activeBoardId);

        for(int i = 0; i < 9; i++) {
            boolean isSubBoardCompleted = isSubBoardCompleted(i);
            boolean shouldEnable = !isSubBoardCompleted && (i == activeBoardId || isActiveBoardCompleted);
            view.setSubBoardEnabled(i, shouldEnable);
        }
        view.refresh();
    }

    private boolean isSubBoardCompleted(int index) {
        SubBoard subBoard = view.getSubBoard(index);
        return subBoard.isCompleted();
    }
}
