package view;

import javax.swing.*;
import java.awt.*;

import static utils.Customization.FIELD_BORDER_COLOR;
import static utils.Customization.FIELD_ENABLED_COLOR;
import static utils.Customization.FIELD_SIZE;

public class Field extends JButton {

    private static int nextId = 0;

    private final int id;

   public Field() {
        id = nextId++;
        this.setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));
        this.setBackground(FIELD_ENABLED_COLOR);
        this.setBorder(BorderFactory.createLineBorder(FIELD_BORDER_COLOR));
   }

   public int getId() {
        return id;
    }
}
