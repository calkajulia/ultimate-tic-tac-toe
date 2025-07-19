import javax.swing.*;
import java.awt.*;

class Customization {

    public static final int BOARD_SIZE = 900;
    public static final int FIELD_SIZE = BOARD_SIZE / 9;

    public static final ImageIcon IMAGE_O = loadImageIcon("O.png", FIELD_SIZE, FIELD_SIZE);
    public static final ImageIcon IMAGE_X = loadImageIcon("X.png", FIELD_SIZE, FIELD_SIZE);

    public static final Color COLOR_1 = new Color(0x0c1c2c);
    public static final Color COLOR_2 = new Color(0x1f3c5a);

    public static final Color FIELD_ENABLED_COLOR = new Color(0x173D14);
    public static final Color FIELD_DISABLED_COLOR = new Color(0xC0C0C0);
    public static final Color FIELD_BORDER_COLOR = Color.GRAY;

    public static final Color TEXT_COLOR = Color.WHITE;
    public static final String FONT_NAME = "Berlin Sans FB";

    private Customization() {}

    private static ImageIcon loadImageIcon(String path, int width, int height) {
        try {
            ImageIcon originalIcon = new ImageIcon(path);
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("Couldn't load image: " + path);
            return new ImageIcon();
        }
    }
}
