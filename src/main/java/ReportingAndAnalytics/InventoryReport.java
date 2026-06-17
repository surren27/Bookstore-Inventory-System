
package ReportingAndAnalytics;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * InventoryReport.java
 * GUI for "Generate Inventory Report" use case (Sequence Diagram 3).
 * Actors: Manager & Staff
 *
 * Flow:
 * 2. (Optional) Apply Filter (Category/Supplier)
 * 3-9. getInventoryData(filter)
 * 10. Display Inventory Report
 * 2a. No filter applied -> use all items
 * 9a. Stock data missing -> "Inventory data not available"
 * 9b. System error -> retry option provided
 */

public class InventoryReport extends JFrame {
    private InventoryController inventoryController;
    private DefaultTableModel tableModel;
    private JTextField filterField;

    public InventoryReport(InventoryController inventoryController) {
        this.inventoryController = inventoryController;

        setTitle("Generate Inventory Report");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Filter by Category (optional):"));
        filterField = new JTextField(15);
        topPanel.add(filterField);

        JButton generateBtn = new JButton("Generate Inventory Report");
        topPanel.add(generateBtn);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Book ID", "Title", "Stock", "Min Stock", "Status"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 1. Select Inventory Report -> 3. getInventoryData(filter)
        generateBtn.addActionListener(e -> loadReport());
        generateBtn.addActionListener(e -> {
    System.out.println("Button clicked"); // debug
    loadReport();
});

        // Load initial report with no filter (2a. use all items)
        loadReport();
    }

    private void loadReport() {
        
        String filter = filterField.getText().trim();

        // 3-9. getInventoryData(filter)
        List<Object[]> report = inventoryController.getInventoryData(filter);

        tableModel.setRowCount(0);

        if (report == null) {
            // 9a. "Inventory data not available" / 9b. system error with retry option
            int choice = JOptionPane.showConfirmDialog(this,
                    "Inventory data not available.\nWould you like to retry?",
                    "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                loadReport(); // 9b. retry option provided
            }
            return;
        }
        

        // 10. Display InventoryReport
        for (Object[] row : report) {
            Book b = (Book) row[0];
            String status = (String) row[1];
            tableModel.addRow(new Object[]{b.getBookId(), b.getTitle(), b.getStock(), b.getMinStock(), status});
        }
    }   
}
