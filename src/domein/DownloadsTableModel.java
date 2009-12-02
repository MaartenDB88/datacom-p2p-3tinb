/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domein;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class DownloadsTableModel extends AbstractTableModel {
    Downloads download ;
    private String[] columnNames = {"Name", "Progress"};

    public DownloadsTableModel(Downloads d)
    {
         this.download = d;
         this.download.setModel(this);
    }

    public int getRowCount() {
        return download.getLijst().length;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return download.getLijst()[0].name;
            case 1:
                return download.getLijst()[0].progress + " " + "%";
            case 2:
                return null;
            default:
                return null;

        }
    }


    public String getColumnName(
            int col) {
        return columnNames[col];

    }

    public void update() {
        this.fireTableDataChanged();
    }

}
