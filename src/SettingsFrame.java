import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {

    private boolean startVersion;

    private JRadioButton playAloneButton;
    private JRadioButton playWithComputerButton;

    public SettingsFrame(boolean startVersion) {
        this.startVersion = startVersion;

        initializeFrame();
        initializeComponents();
        this.setVisible(true);
    }

    private void initializeFrame() {
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        if(startVersion) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Ultimate Tick-Tack-Toe");
        } else {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setTitle("Settings");
        }
    }

    private void initializeComponents() {
        JPanel playOptionsPanel = createPlayOptionsPanel();
        this.add(playOptionsPanel, BorderLayout.NORTH);

        JPanel computerOptionsPanel = createComputerOptionsPanel();
        this.add(computerOptionsPanel, BorderLayout.CENTER);

        addPlayOptionsButtonsListeners(computerOptionsPanel);

        JButton actionButton = createActionButton();
        this.add(actionButton, BorderLayout.SOUTH);
    }

    private JPanel createPlayOptionsPanel() {
        ButtonGroup playOptionsGroup = new ButtonGroup();
        playAloneButton = createRadioButton("Play alone", playOptionsGroup, true);
        playWithComputerButton = createRadioButton("Play with computer", playOptionsGroup, false);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Game mode"));
        panel.add(playAloneButton);
        panel.add(playWithComputerButton);

        return panel;
    }

    private JPanel createComputerOptionsPanel() {
        ButtonGroup computerOptionsGroup = new ButtonGroup();
        JRadioButton playerStartsButton = createRadioButton("Player starts", computerOptionsGroup, true);
        JRadioButton computerStartsButton = createRadioButton("Computer starts", computerOptionsGroup, false);

        JComboBox<String> difficultyLevelComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        difficultyLevelComboBox.setFont(new Font(Customization.FONT_NAME, Font.PLAIN, 15));

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Computer options"));
        panel.add(playerStartsButton);
        panel.add(computerStartsButton);
        panel.add(new JLabel("Difficulty Level: "));
        panel.add(difficultyLevelComboBox);
        panel.setVisible(false);

        return panel;
    }

    private JRadioButton createRadioButton(String text, ButtonGroup group, boolean selected) {
        JRadioButton button = new JRadioButton(text);
        button.setFont(new Font(Customization.FONT_NAME, Font.PLAIN, 15));
        button.setSelected(selected);
        group.add(button);
        return button;
    }

    private void addPlayOptionsButtonsListeners(JPanel computerOptionsPanel) {
        playAloneButton.addActionListener(e -> {
            computerOptionsPanel.setVisible(false);
            revalidate();
            repaint();
        });

        playWithComputerButton.addActionListener(e -> {
            computerOptionsPanel.setVisible(true);
            revalidate();
            repaint();
        });
    }

    private JButton createActionButton() {
        JButton button;
        if(startVersion) {
            button = new JButton("START");
            button.addActionListener(e -> handleStartButtonClick());
        } else {
            button = new JButton("APPLY CHANGES");
            button.addActionListener(e -> handleApplyChangesButtonClick());
        }
        button.setFont(new Font(Customization.FONT_NAME, Font.PLAIN, 15));
        button.setBackground(Customization.COLOR_1);
        button.setForeground(Customization.TEXT_COLOR);
        return button;
    }

    private void handleStartButtonClick() {
        if(playWithComputerButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Currently playing with computer is not available");
        } else {
            TickTackToeModel model = new TickTackToeModel();
            TickTackToeView view = new TickTackToeView();
            new TickTackToeController(model, view);
            dispose();
        }
    }

    private void handleApplyChangesButtonClick() {
        if(playWithComputerButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Currently switching between game modes is not available");
        } else {
            JOptionPane.showMessageDialog(this, "You are already in this mode");
        }
    }
}
