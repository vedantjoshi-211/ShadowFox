package com.example.inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Simple Inventory Management System using Swing.
 * Generated on 2025-06-26.
 */
public class InventoryManagerSwing {

    private final InventoryTableModel model = new InventoryTableModel();
    private final JTable table = new JTable(model);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryManagerSwing::new);
    }

    public InventoryManagerSwing() {
        JFrame frame = new JFrame("Inventory Manager - Swing v1");
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField nameField = new JTextField(10);
        JTextField qtyField = new JTextField(5);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(qtyField);
        inputPanel.add(addBtn);
        inputPanel.add(updateBtn);
        inputPanel.add(deleteBtn);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        ActionListener addListener = e -> {
            String name = nameField.getText().trim();
            int qty;
            try {
                qty = Integer.parseInt(qtyField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Quantity must be a number");
                return;
            }
            if (!name.isEmpty()) {
                model.addItem(name, qty);
                nameField.setText("");
                qtyField.setText("");
            }
        };
        addBtn.addActionListener(addListener);

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row first");
                return;
            }
            String name = nameField.getText().trim();
            int qty;
            try {
                qty = Integer.parseInt(qtyField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Quantity must be a number");
                return;
            }
            model.updateItem(row, name, qty);
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row first");
                return;
            }
            model.deleteItem(row);
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
