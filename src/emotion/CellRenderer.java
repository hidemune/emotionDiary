/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emotion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellRenderer extends JLabel
        implements TableCellRenderer {

    public CellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {
        
        this.setHorizontalAlignment(JLabel.CENTER);
        
        setText(value.toString());
        
        this.setOpaque(false);
        this.setBorder(super.getBorder());
        if (isSelected) {
            setForeground(new Color(0xFF0000));
            setBackground(new Color(0x0000FF));
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        try{
            if (Integer.parseInt(value.toString().replace("●", "")) > 0) {
                //Do Nothing
            }
        }catch (Exception e) {
            this.setForeground(new Color(0x999999));
        }
        return this;
    }

}
