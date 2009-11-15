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
       
      return controller.getBestanden().size();

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(
            int row, int col) {

        switch (col) {
            case 0:
                return controller.getBestanden().get(row).getBestand().getName();
            case 1:
                return String.format(controller.getBestanden().get(row).getSize() /1000 + " " + "KB");
            default:
                return null;
        }

    }



    @Override
    public String getColumnName(
            int col) {
        return columnNames[col];

    }

    public void update() {
        this.fireTableDataChanged();
    }
}
