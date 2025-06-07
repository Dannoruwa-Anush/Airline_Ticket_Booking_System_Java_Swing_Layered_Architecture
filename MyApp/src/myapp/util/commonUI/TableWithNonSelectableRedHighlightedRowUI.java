/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.commonUI;

import java.awt.Color;
import java.awt.Component;
import java.util.function.Predicate;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author HP
 */
public class TableWithNonSelectableRedHighlightedRowUI {

    public static void markAndDisableRows(
            JTable table,
            int conditionColumnIndex,
            Predicate<Object> disableCondition
    ) {
        // Set cell renderer to mark rows in red that match the condition
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                try {
                    Object cellValue = table.getValueAt(row, conditionColumnIndex);
                    if (disableCondition.test(cellValue)) {
                        c.setBackground(Color.RED);
                        c.setForeground(Color.WHITE);
                    } else {
                        if (isSelected) {
                            c.setBackground(table.getSelectionBackground());
                            c.setForeground(table.getSelectionForeground());
                        } else {
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                        }
                    }
                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });

        // Custom selection model to prevent selection
        ListSelectionModel customSelectionModel = new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                try {
                    Object cellValue = table.getValueAt(index0, conditionColumnIndex);
                    if (!disableCondition.test(cellValue)) {
                        super.setSelectionInterval(index0, index1);
                    } else {
                        super.clearSelection();
                    }
                } catch (Exception e) {
                    super.clearSelection();
                }
            }

            @Override
            public void addSelectionInterval(int index0, int index1) {
                try {
                    Object cellValue = table.getValueAt(index0, conditionColumnIndex);
                    if (!disableCondition.test(cellValue)) {
                        super.addSelectionInterval(index0, index1);
                    }
                } catch (Exception e) {
                    // Do nothing
                }
            }
        };

        table.setSelectionModel(customSelectionModel);
    }
}
