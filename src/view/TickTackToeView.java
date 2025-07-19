package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static utils.Customization.BOARD_SIZE;
import static utils.Customization.COLOR_1;
import static utils.Customization.COLOR_2;
import static utils.Customization.FIELD_DISABLED_COLOR;
import static utils.Customization.FIELD_ENABLED_COLOR;
import static utils.Customization.FONT_NAME;
import static utils.Customization.TEXT_COLOR;

public class TickTackToeView extends JFrame {

    private JPanel boardPanel;
    private ArrayList<SubBoard> subBoards;
    private JLabel turnLabel;
    private JTextArea gameHistoryArea;

    public TickTackToeView() {
        subBoards = new ArrayList<>();

        initializeFrame();
        initializeComponents();
        this.setVisible(true);
    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(BOARD_SIZE + 300, BOARD_SIZE + 100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Ultimate Tic-Tac-Toe");
    }

    private void initializeComponents() {
        initializeNorthPanel();
        initializeBoardPanel();
        initializeHistoryPanel();
    }

    private void initializeNorthPanel() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(COLOR_1);

        turnLabel = new JLabel("0's turn");
        turnLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 50));
        turnLabel.setBackground(COLOR_1);
        turnLabel.setForeground(TEXT_COLOR);
        turnLabel.setOpaque(true);
        northPanel.add(turnLabel, BorderLayout.CENTER);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font(FONT_NAME, Font.PLAIN, 50));
        settingsButton.setBackground(COLOR_1);
        settingsButton.setForeground(TEXT_COLOR);
        settingsButton.setFocusable(false);
        settingsButton.addActionListener(e -> new SettingsFrame(false));
        northPanel.add(settingsButton, BorderLayout.EAST);

        this.add(northPanel, BorderLayout.NORTH);
    }

    private void initializeBoardPanel() {
        boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        this.add(boardPanel, BorderLayout.CENTER);
    }

    private void initializeHistoryPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout());

        JLabel gameHistoryLabel = new JLabel("Game History");
        gameHistoryLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 30));
        gameHistoryLabel.setBackground(COLOR_2);
        gameHistoryLabel.setForeground(TEXT_COLOR);
        gameHistoryLabel.setOpaque(true);
        gameHistoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        historyPanel.add(gameHistoryLabel, BorderLayout.NORTH);

        gameHistoryArea = new JTextArea(20, 20);
        gameHistoryArea.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
        gameHistoryArea.setBackground(COLOR_2);
        gameHistoryArea.setForeground(TEXT_COLOR);
        gameHistoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameHistoryArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(historyPanel, BorderLayout.EAST);
    }

    public SubBoard getSubBoard(int index) {
        return subBoards.get(index);
    }

    public void addSubBoard(SubBoard subBoard) {
        boardPanel.add(subBoard);
        subBoards.add(subBoard);
    }

    public void setSubBoardEnabled(int index, boolean enabled) {
        SubBoard subBoard = subBoards.get(index);
        if(enabled) {
            enableFields(subBoard);
        } else {
            disableFields(subBoard);
        }
    }

    private void enableFields(SubBoard subBoard) {
        for(Field field : subBoard.fields)
            if(field.getIcon() == null) {
                field.setBackground(FIELD_ENABLED_COLOR);
                field.setEnabled(true);
            }
    }

    private void disableFields(SubBoard subBoard) {
        for(Field field : subBoard.fields)
            if(field.getIcon() == null) {
                field.setBackground(FIELD_DISABLED_COLOR);
                field.setEnabled(false);
            }
    }

    public void updateTurnLabel(String text) {
        turnLabel.setText(text);
    }

    public void updateGameHistory(ArrayList<String> gameHistory) {
        StringBuilder sb = new StringBuilder();
        for(String s : gameHistory) {
            sb.append(s).append("\n");
        }
        gameHistoryArea.setText(sb.toString());
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}
