import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SubBoard extends JPanel {

    public enum Status {ACTIVE, COMPLETED}

    private static int nextId = 0;

    private final int id;
    private Status status;
    private char[][] board;

    public ArrayList<Field> fields;
    private JPanel panel;
    private JLayeredPane layeredPane;
    private JButton winningButton;

    public SubBoard() {
        id = nextId++;
        status = Status.ACTIVE;
        board = new char[3][3];

        initializeComponents();
    }

    private void initializeComponents() {
        initializeBoard();
        fields = new ArrayList<>();
        initializeFields();
    }

    private void initializeBoard() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Customization.FIELD_SIZE * 3, Customization.FIELD_SIZE * 3));
        this.setBorder(BorderFactory.createLineBorder(Customization.COLOR_2, 3));

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(Customization.FIELD_SIZE * 3, Customization.FIELD_SIZE * 3));
        this.add(layeredPane, BorderLayout.CENTER);

        panel = new JPanel(new GridLayout(3, 3));
        panel.setBounds(0, 0, Customization.FIELD_SIZE * 3, Customization.FIELD_SIZE * 3);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
    }

    private void initializeFields() {
        for(int i = 0; i < 9; i++) {
            Field field = new Field();
            panel.add(field);
            fields.add(field);
        }
    }

    public int getId() {
        return id;
    }

    public char[][] getBoard() {
        return board;
    }

    public void updateBoard(int row, int column, char player) {
        board[row][column] = player;
    }

    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void displayWinner(char winner) {
        winningButton = createWinningButton(winner);
        layeredPane.add(winningButton, JLayeredPane.PALETTE_LAYER);
        this.revalidate();
        this.repaint();
    }

    public JButton createWinningButton(char winner) {
        JButton button  = new JButton();
        button.setText(String.valueOf(winner));
        button.setBounds(0, 0, Customization.FIELD_SIZE * 3, Customization.FIELD_SIZE * 3);
        button.setFont(new Font(Customization.FONT_NAME, Font.BOLD, 100));
        button.setForeground(Customization.COLOR_1);
        button.setEnabled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setOpaque(false);
                button.setText(null);
                revalidate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(true);
                button.setBorderPainted(true);
                button.setOpaque(true);
                button.setText(String.valueOf(winner));
                revalidate();
                repaint();
            }
        });
        return button;
    }
}
