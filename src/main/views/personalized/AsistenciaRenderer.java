package main.views.personalized;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class AsistenciaRenderer extends JLabel implements TableCellRenderer {
    public AsistenciaRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if ("Presente".equals(value)) {
            setText("●"); 
            setForeground(Color.GREEN);
        } else if ("Ausente".equals(value)) {
            setText("●");
            setForeground(Color.RED);
        } else {
            setText("○");
            setForeground(Color.GRAY);
        }

        return this;
    }
}

