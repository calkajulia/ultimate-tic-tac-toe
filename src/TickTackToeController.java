import javax.swing.*;

public class TickTackToeController {
    private TickTackToeModel model;
    private TickTackToeView view;
    private int activePanelId;

    public TickTackToeController(TickTackToeModel model, TickTackToeView view) {
        this.model = model;
        this.view = view;
        activePanelId = -1;

        initializeBoard();
    }

    private void initializeBoard() {
        for(int i = 0; i < 9; i++) {
            SubPanel subPanel = new SubPanel();
            for(Field field : subPanel.fields) {
                field.addActionListener(e -> fieldActions(subPanel, field));
            }
            view.addSubPanel(subPanel);
        }
        view.revalidate();
        view.repaint();
    }

    private void fieldActions(SubPanel subPanel, Field field) {
        char currentPlayer = model.getCurrentPlayer();
        ImageIcon currentPlayerIcon = (currentPlayer == '0') ? Customization.image0 : Customization.imageX;
        char nextPlayer = (currentPlayer == '0') ? 'X' : '0';

        field.setIcon(currentPlayerIcon);
        field.setEnabled(false);

        view.updateTurnLabel(nextPlayer + "'s turn");

        model.addMoveToGameHistory(subPanel.getId(), field.getId() % 9);
        view.updateGameHistory(model.getGameHistory());
        model.incrementTurn();

        subPanel.updateSubPanelBoard((field.getId() % 9) / 3, (field.getId() % 9) % 3, currentPlayer);
        subPanel.updateIsDraw();

        char subPanelWinner = subPanel.checkIfSubPanelIsWon();
        if(subPanelWinner != '\0') {
            model.updateSubPanelWins(subPanel.getId() / 3, subPanel.getId() % 3, subPanelWinner);
            subPanel.displayWinningButton(subPanelWinner);
        }
        else if(subPanel.getIsDraw()) {
            model.updateSubPanelWins(subPanel.getId() / 3, subPanel.getId() % 3, 'D');
            subPanel.displayWinningButton(' ');
        }

        char overallWinner = model.checkOverallWin();
        if(overallWinner != '\0'){
            JOptionPane.showMessageDialog(view, "Player " + overallWinner + " won!");
        }
        else if(model.checkOverallDraw()) {
            JOptionPane.showMessageDialog(view, "It's a draw!");
        }
        else {
            activePanelId = field.getId() % 9;
            view.updatePanels(activePanelId, model);
        }
    }
}
