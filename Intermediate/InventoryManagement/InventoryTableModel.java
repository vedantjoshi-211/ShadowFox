package com.example.inventory;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing TableModel holding inventory items.
 * Generated on 2025-06-26.
 */
public class InventoryTableModel extends AbstractTableModel {

    private final String[] columns = { "ID", "Name", "Quantity" };
    private final List<InventoryItem> items = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventoryItem item = items.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getName();
            case 2 -> item.getQuantity();
            default -> null;
        };
    }

    public void addItem(String name, int qty) {
        items.add(new InventoryItem(nextId++, name, qty));
        fireTableDataChanged();
    }

    public void updateItem(int row, String name, int qty) {
        InventoryItem item = items.get(row);
        item.setName(name);
        item.setQuantity(qty);
        fireTableRowsUpdated(row, row);
    }

    public void deleteItem(int row) {
        items.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
