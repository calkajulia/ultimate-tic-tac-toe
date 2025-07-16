import javax.swing.*;
import java.awt.*;

class Customization {
    public static int boardSize, buttonSize;
    public static ImageIcon icon, image0, imageX;
    public static Color buttonEnabledColor, buttonDisabledColor, buttonBorderColor;
    public static Color northPanelColor, gameHistoryColor, subPanelBorderColor;
    public static Color textColor;
    public static String fontName;

    static {
        boardSize = 900;
        buttonSize = boardSize / 9;

        icon = loadImageIcon("icon.png", 32, 32);
        image0 = loadImageIcon("0.png", buttonSize, buttonSize);
        imageX = loadImageIcon("X.png", buttonSize,buttonSize);

        buttonEnabledColor = new Color(0x173D14);
        buttonDisabledColor = new Color(0xC0C0C0);
        buttonBorderColor = Color.GRAY;

        northPanelColor = new Color(0x0c1c2c);
        gameHistoryColor = new Color(0x1f3c5a);
        subPanelBorderColor = new Color(0x1f3c5a);

        textColor = Color.WHITE;

        fontName = "Berlin Sans FB";
    }

    private static ImageIcon loadImageIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
