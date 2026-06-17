
package ReportingAndAnalytics;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * BestSellingBooks.java
 * GUI for "View Best-Selling Books" use case (Sequence Diagram 4).
 * Actor: Manager
 *
 * Flow:
 * 2. (Optional) Select Ranking Criteria (Revenue/Quantity)
 * 3-8. getBestSelling(criteria)
 * 9. Display top 10 books
 * 8a. Insufficient data -> "Not enough data for ranking"
 */
public class BestSellingBooks extends JFrame {
    private ReportController reportController;
    private DefaultTableModel tableModel;
    private JComboBox<String> criteriaBox;

    public BestSellingBooks(ReportController reportController) {
        this.reportController = reportController;

        setTitle("View Best-Selling Books");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Ranking Criteria:"));
        criteriaBox = new JComboBox<>(new String[]{"Revenue", "Quantity"});
        topPanel.add(criteriaBox);

        JButton viewBtn = new JButton("View Best-Selling Books");
        topPanel.add(viewBtn);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Rank", "Book ID", "Title", "Value"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 1. Select Best-Selling Report -> 3. getBestSelling(criteria)
        viewBtn.addActionListener(e -> loadReport());

        loadReport();
    }

    private void loadReport() {
        String criteria = (String) criteriaBox.getSelectedItem();

        // 3-8. getBestSelling(criteria)
        List<Object[]> ranking = reportController.getBestSellingBooks(criteria);

        tableModel.setRowCount(0);

        if (ranking == null) {
            // 8a. "Not enough data for ranking"
            JOptionPane.showMessageDialog(this, "Not enough data for ranking",
                    "Insufficient Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 9. Display top 10 books
        int rank = 1;
        for (Object[] row : ranking) {
            Book b = (Book) row[0];
            double value = (double) row[1];
            tableModel.addRow(new Object[]{rank++, b.getBookId(), b.getTitle(), String.format("%.2f", value)});
        }
    }
}   
