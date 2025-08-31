package main.views.personalized;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class AsistenciaEditor extends AbstractCellEditor implements TableCellEditor {
    private String estado;
    private JButton button;

    public AsistenciaEditor() {
        button = new JButton("●");
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("Presente".equals(estado)) {
                    estado = "Ausente";
                } else {
                    estado = "Presente";
                }
                fireEditingStopped();
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return estado;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        estado = (value == null) ? "Ausente" : value.toString();
        return button;
    }
}

