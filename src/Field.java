import javax.swing.*;
import java.awt.*;

public class Field extends JButton {
    private static int nextId = 0;
    private int id;

   public Field() {
        id = nextId++;
        this.setPreferredSize(new Dimension(Customization.buttonSize, Customization.buttonSize));
        this.setBackground(Customization.buttonEnabledColor);
        this.setBorder(BorderFactory.createLineBorder(Customization.buttonBorderColor));
    }

    public int getId() {
        return id;
    }
}
