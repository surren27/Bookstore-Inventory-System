
package ReportingAndAnalytics;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * DashboardFrame.java
 * GUI for "Dashboard Overview" use case (Sequence Diagram 8).
 * Actors: Manager & Staff
 *
 * Flow:
 * 1. Login (already done before opening this frame)
 * 2. loadDashboard() -> 3-12
 * 13. Display Dashboard (Sales, Inventory, Best Sellers)
 * 2a. User refreshes dashboard
 * 12a. System fails to load data -> "Dashboard unavailable"
 */
public class DashboardOverview extends JFrame {
    private DashboardController dashboardController;
    private JLabel totalSalesLabel;
    private JTextArea inventoryArea;
    private JTextArea bestSellersArea;

    public DashboardOverview(DashboardController dashboardController, String role) {
        this.dashboardController = dashboardController;

        setTitle("Dashboard Overview - " + role);
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Dashboard Overview", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalSalesLabel = new JLabel("Total Sales: -");
        totalSalesLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        centerPanel.add(totalSalesLabel);

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(new JLabel("Inventory Status (Low Stock Items):"));
        inventoryArea = new JTextArea(5, 30);
        inventoryArea.setEditable(false);
        centerPanel.add(new JScrollPane(inventoryArea));

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(new JLabel("Best-Selling Books:"));
        bestSellersArea = new JTextArea(5, 30);
        bestSellersArea.setEditable(false);
        centerPanel.add(new JScrollPane(bestSellersArea));

        add(centerPanel, BorderLayout.CENTER);

        // 2a. User refreshes dashboard
        JButton refreshBtn = new JButton("Refresh");
        add(refreshBtn, BorderLayout.SOUTH);
        refreshBtn.addActionListener(e -> loadDashboard());

        // 2. loadDashboard()
        loadDashboard();
    }

    private void loadDashboard() {
        // 2-12. loadDashboard() -> getSummaryData()
        ServiceLayer.DashboardData data = dashboardController.loadDashboard();

        if (data == null) {
            // 12a. "Dashboard unavailable"
            totalSalesLabel.setText("Dashboard unavailable");
            inventoryArea.setText("");
            bestSellersArea.setText("");
            return;
        }

        // 13. Display Dashboard (Sales, Inventory, Best Sellers)
        totalSalesLabel.setText(String.format("Total Sales: %.2f", data.totalSales));

        StringBuilder inv = new StringBuilder();
        if (data.lowStockBooks.isEmpty()) {
            inv.append("No low stock items.");
        } else {
            for (Book b : data.lowStockBooks) {
                inv.append(b.getBookId()).append(" - ").append(b.getTitle())
                        .append(" (Stock: ").append(b.getStock())
                        .append(", Min: ").append(b.getMinStock()).append(")\n");
            }
        }
        inventoryArea.setText(inv.toString());

        StringBuilder best = new StringBuilder();
        for (Map.Entry<String, Double> entry : data.topBestSellers.entrySet()) {
            best.append(entry.getKey()).append(": ").append(String.format("%.2f", entry.getValue())).append("\n");
        }
        bestSellersArea.setText(best.toString());
    }
}   

