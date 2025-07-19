import javax.swing.*;
import java.awt.*;

public class Field extends JButton {

    private static int nextId = 0;

    private final int id;

   public Field() {
        id = nextId++;
        this.setPreferredSize(new Dimension(Customization.FIELD_SIZE, Customization.FIELD_SIZE));
        this.setBackground(Customization.FIELD_ENABLED_COLOR);
        this.setBorder(BorderFactory.createLineBorder(Customization.FIELD_BORDER_COLOR));
   }

   public int getId() {
        return id;
    }
}
