
package ReportingAndAnalytics;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * LowSalesBook.java
 * GUI for "View Low Sales Books" use case (Sequence Diagram 5).
 * Actor: Manager
 *
 * Flow:
 * 2. (Optional) Set threshold
 * 3-7. getLowSales(threshold)
 * 8. Display Low Sales Books
 * 7a. No low sales items -> "All books performing well"
 */
public class LowSalesBook extends JFrame {
    private ReportController reportController;
    private DefaultTableModel tableModel;
    private JTextField thresholdField;

    public LowSalesBook(ReportController reportController) {
        this.reportController = reportController;

        setTitle("View Low Sales Books");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Threshold (units sold):"));
        thresholdField = new JTextField("5", 5);
        topPanel.add(thresholdField);

        JButton viewBtn = new JButton("View Low Sales Books");
        topPanel.add(viewBtn);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Book ID", "Title", "Total Sold"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 1. Select Low Sales Report -> 3. getLowSales(threshold)
        viewBtn.addActionListener(e -> loadReport());

        loadReport();
    }

    private void loadReport() {
        int threshold;
        try {
            threshold = Integer.parseInt(thresholdField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Threshold must be a number.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. (Optional) Set threshold -> 3-7. getLowSales(threshold)
        List<Object[]> result = reportController.getLowSales(threshold);

        tableModel.setRowCount(0);

        if (result == null || result.isEmpty()) {
            // 7a. "All books performing well"
            JOptionPane.showMessageDialog(this, "All books performing well",
                    "Low Sales Report", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 8. Display Low Sales Books
        for (Object[] row : result) {
            Book b = (Book) row[0];
            int soldQty = (int) row[1];
            tableModel.addRow(new Object[]{b.getBookId(), b.getTitle(), soldQty});
        }
    }
}   

