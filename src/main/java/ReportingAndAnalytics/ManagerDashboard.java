
package ReportingAndAnalytics;


import javax.swing.*;
import java.awt.*;

/**
 * ManagerDashboard.java
 * Main menu for Manager actor.
 * Use cases accessible (per Use Case Diagram - Manager connects to):
 * Generate Inventory Report, Generate Sales Report, View Best-Selling Books,
 * View Low Sales Books, Generate Financial Report, View Sales Trend Analytics,
 * Dashboard Overview.
 */
public class ManagerDashboard extends JFrame {

    public ManagerDashboard(String role, InventoryController inventoryController,
                                  ReportController reportController,
                                  FinancialService financialService,
                                  AnalyticsController analyticsController,
                                  DashboardController dashboardController) {
        setTitle("Book Inventory and Sales Management System - Manager Menu");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Manager Menu", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton inventoryReportBtn = new JButton("Generate Inventory Report");
        JButton salesReportBtn = new JButton("Generate Sales Report");
        JButton bestSellingBtn = new JButton("View Best-Selling Books");
        JButton lowSalesBtn = new JButton("View Low Sales Books");
        JButton financialReportBtn = new JButton("Generate Financial Report");
        JButton salesTrendBtn = new JButton("View Sales Trend Analytics");
        JButton dashboardBtn = new JButton("Dashboard Overview");

        panel.add(inventoryReportBtn);
        panel.add(salesReportBtn);
        panel.add(bestSellingBtn);
        panel.add(lowSalesBtn);
        panel.add(financialReportBtn);
        panel.add(salesTrendBtn);
        panel.add(dashboardBtn);

        add(panel, BorderLayout.CENTER);

        // 1. Select "Inventory Report"
        inventoryReportBtn.addActionListener(e -> new InventoryReport(inventoryController).setVisible(true));

        // 1. Select "Sales Report"
        salesReportBtn.addActionListener(e -> new SalesReport(reportController).setVisible(true));

        // 1. Select "Best-Selling Report"
        bestSellingBtn.addActionListener(e -> new BestSellingBooks(reportController).setVisible(true));

        // 1. Select "Low Sales Report"
        lowSalesBtn.addActionListener(e -> new LowSalesBook(reportController).setVisible(true));

        // 1. Select "Financial Report"
        financialReportBtn.addActionListener(e -> new FinancialReport(financialService).setVisible(true));

        // 1. Select "Sales Trend"
        salesTrendBtn.addActionListener(e -> new SalesTrendAnalytics(analyticsController).setVisible(true));

        // 1. Login -> loadDashboard()
        dashboardBtn.addActionListener(e -> new DashboardOverview(dashboardController, "Manager").setVisible(true));
    }
}    
