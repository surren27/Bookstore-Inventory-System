
package ReportingAndAnalytics;


import javax.swing.*;
import java.awt.*;

/**
 * StaffDashboardFrame.java
 * Main menu for Staff actor.
 * Use cases accessible: Record Sales, Generate Inventory Report, Dashboard Overview.
 * (per Use Case Diagram - Staff connects to these three use cases)
 */
public class StaffDashboardFrame extends JFrame {

    public StaffDashboardFrame(String role, SalesController salesController,
                                InventoryController inventoryController,
                                ReportController reportController,
                                DashboardController dashboardController) {
        setTitle("Book Inventory and Sales Management System - Staff Menu");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Staff Menu", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton recordSalesBtn = new JButton("Record Sales");
        JButton inventoryReportBtn = new JButton("Generate Inventory Report");
        JButton dashboardBtn = new JButton("Dashboard Overview");

        panel.add(recordSalesBtn);
        panel.add(inventoryReportBtn);
        panel.add(dashboardBtn);

        add(panel, BorderLayout.CENTER);

        // 1. Select "Record Sales"
        recordSalesBtn.addActionListener(e -> new RecordSales(salesController).setVisible(true));

        // 1. Select "Inventory Report"
        inventoryReportBtn.addActionListener(e -> new InventoryReport(inventoryController).setVisible(true));

        // 1. Login -> loadDashboard()
        dashboardBtn.addActionListener(e -> new DashboardOverview(dashboardController, "Staff").setVisible(true));
    }
}   

