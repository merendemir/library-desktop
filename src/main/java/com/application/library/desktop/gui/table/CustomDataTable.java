package com.application.library.desktop.gui.table;


import com.application.library.desktop.constants.SystemVariables;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomDataTable extends JTable {
    private final int ID_COLUMN_INDEX;
    String[] columnNames;
    private final DefaultTableModel defaultTableModel;

    public CustomDataTable(String[] columnNames, int idColumnIndex) {
        this.columnNames = columnNames;
        this.ID_COLUMN_INDEX = idColumnIndex;

        defaultTableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setModel(defaultTableModel);

        getColumnModel().getColumn(ID_COLUMN_INDEX).setMinWidth(0); //hide id column in UI
        getColumnModel().getColumn(ID_COLUMN_INDEX).setMaxWidth(0); //hide id column İn UI

        ListSelectionModel selectionModel = getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        getTableHeader().setReorderingAllowed(false);

        setAutoCreateRowSorter(false);
        setColumnSelectionAllowed(false);
        setDropMode(DropMode.USE_SELECTION);
        setEnabled(true);
        setFillsViewportHeight(true);
        setName("");
        setOpaque(true);
        setRowSelectionAllowed(true);
        setSelectionBackground(new Color(-5789785));
        setUpdateSelectionOnSort(false);
        putClientProperty("Table.isFileList", Boolean.FALSE);
        addRender();
    }

    private void addRender() {
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                final JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(isSelected ? SystemVariables.SYSTEM_DEFAULT_COLOR : row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);

                return c;
            }
        });
    }

    public void addRow(String[] data) {
        defaultTableModel.addRow(data);
    }

    public void removeAllData() {
        defaultTableModel.setRowCount(0);
    }


    public int getID_COLUMN_INDEX() {
        return ID_COLUMN_INDEX;
    }
}
