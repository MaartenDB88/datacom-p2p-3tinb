/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.File;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maarten
 */
public class BestandTableModel extends AbstractTableModel {

    private DomeinController controller;
    private String[] columnNames = {"Name", "Size"};

    public BestandTableModel(DomeinController c) {

        controller = c;

    }

    public int getRowCount() {
       
      return controller.getFiles().size();

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(
            int row, int col) {

        switch (col) {
            case 0:
                return controller.getFiles().get(row).getBestand().getName();
            case 1:
                return String.format(controller.getFiles().get(row).getBestand().length() / 1000.0 + " " + "KB");
            default:
                return null;
        }

    }

    public Class getColumnClass(
            int c) {
        return getValueAt(0, c).getClass();
    }

    public String getColumnName(
            int col) {
        return columnNames[col];

    }

    public void update() {
        this.fireTableDataChanged();
    }
}
