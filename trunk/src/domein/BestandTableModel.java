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

    public Object getValueAt(int row, int col) {


        switch (col) {

            case 0:
                return controller.getFiles().get(row).getBestand().getName();
            case 1:
                int size = (int) controller.getFiles().get(row).getSize() / 1000;
                if(size > 1000)
                return String.format(size / 1000.0 + " " + "MB");
                else
                    return String.format(size + " " + "KB");
            case 2:
                return controller.getFiles().get(row);
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
