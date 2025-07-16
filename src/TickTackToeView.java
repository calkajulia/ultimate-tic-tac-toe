import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TickTackToeView extends JFrame {
    private JPanel boardPanel;
    private ArrayList<SubPanel> subPanels;
    private JLabel turnLabel;
    private JTextArea gameHistoryArea;

    public TickTackToeView() {
        subPanels = new ArrayList<>();

        initializeFrame();
        initializeNorthPanel();
        initializeBoardPanel();
        initializeHistoryPanel();
        this.setVisible(true);
    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Customization.boardSize + 300, Customization.boardSize + 100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Ultimate Tick-Tack-Toe");
        this.setIconImage(Customization.icon.getImage());
    }

    private void initializeNorthPanel() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(Customization.northPanelColor);

        turnLabel = new JLabel("0's turn");
        turnLabel.setFont(new Font(Customization.fontName, Font.PLAIN, 50));
        turnLabel.setBackground(Customization.northPanelColor);
        turnLabel.setForeground(Customization.textColor);
        turnLabel.setOpaque(true);
        northPanel.add(turnLabel, BorderLayout.CENTER);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font(Customization.fontName, Font.PLAIN, 50));
        settingsButton.setBackground(Customization.northPanelColor);
        settingsButton.setForeground(Customization.textColor);
        settingsButton.setFocusable(false);
        settingsButton.addActionListener(e -> new SettingsFrame(false));
        northPanel.add(settingsButton, BorderLayout.EAST);

        this.add(northPanel, BorderLayout.NORTH);
    }

    private void initializeBoardPanel() {
        boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setPreferredSize(new Dimension(Customization.boardSize, Customization.boardSize));
        this.add(boardPanel, BorderLayout.CENTER);
    }

    private void initializeHistoryPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout());

        JLabel gameHistoryLabel = new JLabel("Game History");
        gameHistoryLabel.setFont(new Font(Customization.fontName, Font.PLAIN, 30));
        gameHistoryLabel.setBackground(Customization.gameHistoryColor);
        gameHistoryLabel.setForeground(Customization.textColor);
        gameHistoryLabel.setOpaque(true);
        gameHistoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        historyPanel.add(gameHistoryLabel, BorderLayout.NORTH);

        gameHistoryArea = new JTextArea(20, 20);
        gameHistoryArea.setFont(new Font(Customization.fontName, Font.PLAIN, 15));
        gameHistoryArea.setBackground(Customization.gameHistoryColor);
        gameHistoryArea.setForeground(Customization.textColor);
        gameHistoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameHistoryArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(historyPanel, BorderLayout.EAST);
    }

    public void addSubPanel(SubPanel subPanel) {
        boardPanel.add(subPanel);
        subPanels.add(subPanel);
    }

    public void updatePanels(int activePanelId, TickTackToeModel model) {
        boolean isActivePanelWonOrDrawn = model.isSubPanelWon(activePanelId / 3, activePanelId % 3) || model.isSubPanelDraw(activePanelId / 3, activePanelId % 3);

        for(SubPanel subPanel : subPanels) {
            boolean isSubPanelWonOrDrawn = model.isSubPanelWon(subPanel.getId() / 3, subPanel.getId() % 3) || model.isSubPanelDraw(subPanel.getId() / 3, subPanel.getId() % 3);

            if(!isSubPanelWonOrDrawn && (subPanel.getId() == activePanelId || isActivePanelWonOrDrawn))
                enableFields(subPanel);
            else
                disableFields(subPanel);
        }
        this.revalidate();
        this.repaint();
    }

    private void enableFields(SubPanel subPanel) {
        for(Field field : subPanel.fields)
            if(field.getIcon() == null) {
                field.setBackground(Customization.buttonEnabledColor);
                field.setEnabled(true);
            }
    }

    private void disableFields(SubPanel subPanel) {
        for(Field field : subPanel.fields)
            if(field.getIcon() == null) {
                field.setBackground(Customization.buttonDisabledColor);
                field.setEnabled(false);
            }
    }

    public void updateTurnLabel(String text) {
        turnLabel.setText(text);
    }

    public void updateGameHistory(ArrayList<String> gameHistory) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : gameHistory)
            stringBuilder.append(s).append("\n");
        gameHistoryArea.setText(stringBuilder.toString());
    }
}
