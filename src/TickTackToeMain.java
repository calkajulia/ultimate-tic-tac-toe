import javax.swing.*;

public class TickTackToeMain {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
            new SettingsFrame(true)
        );
    }
}
