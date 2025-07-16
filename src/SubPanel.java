import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SubPanel extends JPanel {
    private static int nextId = 0;
    private int id;
    private boolean isDraw;
    public ArrayList<Field> fields;
    private char[][] board;
    private JLayeredPane layeredPane;
    private JPanel boardPanel;
    private JButton winningButton;

    public SubPanel() {
        id = nextId++;
        isDraw = false;
        fields = new ArrayList<>();
        board = new char[3][3];

        initializePanel();
        initializeFields();
    }

    private void initializePanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Customization.buttonSize * 3, Customization.buttonSize * 3));
        this.setBorder(BorderFactory.createLineBorder(Customization.subPanelBorderColor, 3));

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(Customization.buttonSize * 3, Customization.buttonSize * 3));
        this.add(layeredPane, BorderLayout.CENTER);

        boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBounds(0, 0, Customization.buttonSize * 3, Customization.buttonSize * 3);
        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
    }

    private void initializeFields() {
        for(int i = 0; i < 9; i++) {
            Field field = new Field();
            boardPanel.add(field);
            fields.add(field);
        }
    }

    public int getId() {
        return id;
    }

    public boolean getIsDraw() {
        return isDraw;
    }

    public void updateSubPanelBoard(int rowIndex, int columnIndex, char player) {
        board[rowIndex][columnIndex] = player;
    }

    public char checkIfSubPanelIsWon() {
        for(int i = 0; i < 3; i++) {
            if(checkLine(board[i][0], board[i][1], board[i][2]))
                return board[i][0];
            if(checkLine(board[0][i], board[1][i], board[2][i]))
                return board[0][i];
        }
        if(checkLine(board[0][0], board[1][1], board[2][2]))
            return board[0][0];
        if(checkLine(board[0][2], board[1][1], board[2][0]))
            return board[0][2];
        return '\0';
    }

    private boolean checkLine(char c1, char c2, char c3) {
        return c1 != '\0' && c1 == c2 && c2 == c3;
    }

    public void updateIsDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '\0')
                    return;
        if (checkIfSubPanelIsWon() == '\0')
            isDraw = true;
    }

    public void displayWinningButton(char winner) {
        winningButton = new JButton();
        winningButton.setText(String.valueOf(winner));
        winningButton.setBounds(0, 0, Customization.buttonSize * 3, Customization.buttonSize * 3);
        winningButton.setFont(new Font(Customization.fontName, Font.BOLD, 100));
        winningButton.setForeground(Customization.northPanelColor);
        winningButton.setEnabled(false);

        winningButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                winningButton.setContentAreaFilled(false);
                winningButton.setBorderPainted(false);
                winningButton.setOpaque(false);
                winningButton.setText(null);
                revalidate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                winningButton.setContentAreaFilled(true);
                winningButton.setBorderPainted(true);
                winningButton.setOpaque(true);
                winningButton.setText(String.valueOf(winner));
                revalidate();
                repaint();
            }
        });

        layeredPane.add(winningButton, JLayeredPane.PALETTE_LAYER);
        this.revalidate();
        this.repaint();
    }
}
