import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    private JRadioButton playAloneButton;
    private JRadioButton playWithComputerButton;

    public SettingsFrame(boolean startVersion) {
        initializeFrame(startVersion);
        addPlayOptionsPanel();
        JPanel computerOptionsPanel = addComputerOptionsPanel();
        configureButtonPanel(startVersion, computerOptionsPanel);
    }

    private void initializeFrame(boolean startVersion) {
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        if(startVersion) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Ultimate Tick-Tack-Toe");
        }
        else {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setTitle("Settings");
        }
        this.setIconImage(Customization.icon.getImage());
        this.setVisible(true);
    }

    private void addPlayOptionsPanel() {
        ButtonGroup playOptionsGroup = new ButtonGroup();
        playAloneButton = createRadioButton("Play alone", playOptionsGroup, true);
        playWithComputerButton = createRadioButton("Play with computer", playOptionsGroup, false);

        JPanel playOptionsPanel = new JPanel(new GridLayout(2, 1));
        playOptionsPanel.setBorder(BorderFactory.createTitledBorder("Game mode"));
        playOptionsPanel.add(playAloneButton);
        playOptionsPanel.add(playWithComputerButton);

        this.add(playOptionsPanel, BorderLayout.NORTH);
    }

    private JPanel addComputerOptionsPanel() {
        ButtonGroup computerOptionsGroup = new ButtonGroup();
        JRadioButton playerStartsButton = createRadioButton("Player starts", computerOptionsGroup, true);
        JRadioButton computerStartsButton = createRadioButton("Computer starts", computerOptionsGroup, false);

        JComboBox<String> difficultyLevelComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        difficultyLevelComboBox.setFont(new Font(Customization.fontName, Font.PLAIN, 15));

        JPanel computerOptionsPanel = new JPanel(new GridLayout(4, 1));
        computerOptionsPanel.setBorder(BorderFactory.createTitledBorder("Computer options"));
        computerOptionsPanel.add(playerStartsButton);
        computerOptionsPanel.add(computerStartsButton);
        computerOptionsPanel.add(new JLabel("Difficulty Level: "));
        computerOptionsPanel.add(difficultyLevelComboBox);

        this.add(computerOptionsPanel, BorderLayout.CENTER);
        computerOptionsPanel.setVisible(false);

        return computerOptionsPanel;
    }

    private JRadioButton createRadioButton(String text, ButtonGroup group, boolean selected) {
        JRadioButton button = new JRadioButton(text);
        button.setFont(new Font(Customization.fontName, Font.PLAIN, 15));
        button.setSelected(selected);
        group.add(button);
        return button;
    }

    private void configureButtonPanel(boolean startVersion, JPanel computerOptionsPanel) {
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

        JButton actionButton;
        if(startVersion) {
            actionButton = new JButton("START");
            actionButton.addActionListener(e -> startButtonActions(playWithComputerButton));
        }
        else {
            actionButton = new JButton("APPLY CHANGES");
            actionButton.addActionListener(e -> applyChangesButtonActions(playAloneButton));
        }
        actionButton.setFont(new Font(Customization.fontName, Font.PLAIN, 15));
        actionButton.setBackground(Customization.northPanelColor);
        actionButton.setForeground(Customization.textColor);
        actionButton.setFocusable(false);
        this.add(actionButton, BorderLayout.SOUTH);
    }

    private void startButtonActions(JRadioButton playWithComputerButton) {
        if(playWithComputerButton.isSelected())
            JOptionPane.showMessageDialog(this, "Currently playing with computer is not available");
        else {
            TickTackToeModel model = new TickTackToeModel();
            TickTackToeView view = new TickTackToeView();
            new TickTackToeController(model, view);
            dispose();
        }
    }

    private void applyChangesButtonActions(JRadioButton playAloneButton) {
        if(playAloneButton.isSelected())
            JOptionPane.showMessageDialog(this, "You are already in this mode");
        else
            JOptionPane.showMessageDialog(this, "Currently switching between game modes is not available");
    }
}
