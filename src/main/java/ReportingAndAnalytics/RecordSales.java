
package ReportingAndAnalytics;

import javax.swing.*;
import java.awt.*;

/**
 * RecordSales.java
 * GUI for "Record Sales" use case (Sequence Diagram 1).
 * Actor: Staff
 *
 * Flow:
 * 1. Select Record Sales (this frame opening)
 * 2. Enter Book ID, quantity
 * 11. Confirm Transaction -> 12-20 (handled by SalesController.recordSales)
 * 21. Sale recorded successfully and show success
 * 8a/11a. Error/warning shown via dialogs
 */
public class RecordSales extends JFrame {
    private SalesController salesController;

    private JTextField bookIdField;
    private JTextField quantityField;
    private JLabel totalLabel;
    private JLabel bookDetailsLabel;

    public RecordSales(SalesController salesController) {
        this.salesController = salesController;

        setTitle("Record Sales");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 2. Enter Book ID
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        bookIdField = new JTextField(15);
        add(bookIdField, gbc);

        // 2. Enter quantity
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        quantityField = new JTextField(15);
        add(quantityField, gbc);

        // 7. Book details display
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        bookDetailsLabel = new JLabel(" ");
        add(bookDetailsLabel, gbc);

        // Alternative Flow: total recalculated when quantity edited
        gbc.gridy = 3;
        totalLabel = new JLabel("Total: -");
        add(totalLabel, gbc);

        // Calculate/Preview button (calls getBookDetails + recalculate total)
        gbc.gridy = 4;
        JButton previewBtn = new JButton("Preview");
        add(previewBtn, gbc);

        // 11. Confirm Transaction button
        gbc.gridy = 5;
        JButton confirmBtn = new JButton("Confirm Transaction");
        add(confirmBtn, gbc);

        gbc.gridwidth = 1;

        // Preview button action: 4. getBookDetails, alternative flow recalc total
        previewBtn.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String qtyText = quantityField.getText().trim();

            if (bookId.isEmpty() || qtyText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Book ID and quantity.",
                        "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int qty;
            try {
                qty = Integer.parseInt(qtyText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Book book = salesController.recalculateTotal(bookId, qty) > 0
                    ? null : null; // placeholder, real lookup below

            double total = salesController.recalculateTotal(bookId, qty);
            if (total == 0) {
                bookDetailsLabel.setText("Book not found"); // 8a
                totalLabel.setText("Total: -");
            } else {
                bookDetailsLabel.setText("Found book. Price x Qty calculated.");
                totalLabel.setText(String.format("Total: %.2f", total)); // 3. system calculates total
            }
        });

        // 11. Confirm Transaction -> 12-21
        confirmBtn.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String qtyText = quantityField.getText().trim();

            if (bookId.isEmpty() || qtyText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Book ID and quantity.",
                        "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int qty;
            try {
                qty = Integer.parseInt(qtyText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3-20. recordSales(bookId, qty)
            SalesController.SaleResult result = salesController.recordSales(bookId, qty);

            if (!result.success) {
                // 8a. "Book not found" / 11a. "Insufficient stock"
                JOptionPane.showMessageDialog(this, result.message,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 21. Sale recorded successfully and show success
            String successMsg = result.message
                    + "\nBook: " + result.book.getTitle()
                    + "\nTotal: " + String.format("%.2f", result.total);

            if (result.lowStockAlert) {
                successMsg += "\n\nLow Stock Alert: Stock is below minimum threshold!";
            }

            JOptionPane.showMessageDialog(this, successMsg,
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Reset form
            bookIdField.setText("");
            quantityField.setText("");
            bookDetailsLabel.setText(" ");
            totalLabel.setText("Total: -");
        });
    }
}    

