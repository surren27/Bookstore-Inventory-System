
package ReportingAndAnalytics;

import javax.swing.*;
import java.awt.*;

/**
 * MainApp.java
 * Entry point. Shows a simple login/role selection screen,
 * then opens the Staff or Manager dashboard frame
 * (step 1. Login in Sequence Diagram 8).
 */
public class MainApplicationEntryLoginForm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplicationEntryLoginForm::createAndShowLogin);
    }

    private static void createAndShowLogin() {
        // Initialize shared components
        Database database = new Database();
        InventoryService inventoryService = new InventoryService(database);
        ChartService chartService = new ChartService();
        SalesController salesController = new SalesController(database, inventoryService);
        InventoryController inventoryController = new InventoryController(inventoryService);
        ReportController reportController = new ReportController(database, chartService);
        FinancialService financialService = new FinancialService(database);
        AnalyticsService analyticsService = new AnalyticsService(database);
        AnalyticsController analyticsController = new AnalyticsController(analyticsService, chartService);
        ServiceLayer serviceLayer = new ServiceLayer(database, chartService);
        DashboardController dashboardController = new DashboardController(serviceLayer);

        JFrame loginFrame = new JFrame("Book Inventory and Sales Management System - Login");
        loginFrame.setSize(400, 250);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Select Role to Login", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginFrame.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton staffBtn = new JButton("Login as Staff");
        JButton managerBtn = new JButton("Login as Manager");
        buttonPanel.add(staffBtn);
        buttonPanel.add(managerBtn);

        JPanel wrapper = new JPanel();
        wrapper.add(buttonPanel);
        loginFrame.add(wrapper, BorderLayout.CENTER);

        // 1. Login (Staff) -> 2. loadDashboard()
        staffBtn.addActionListener(e -> {
            loginFrame.dispose();
            new StaffDashboardFrame("Staff", salesController, inventoryController,
                    reportController, dashboardController).setVisible(true);
        });

        // 1. Login (Manager) -> 2. loadDashboard()
        managerBtn.addActionListener(e -> {
            loginFrame.dispose();
            new ManagerDashboard("Manager", inventoryController, reportController,
                    financialService, analyticsController, dashboardController).setVisible(true);
        });

        loginFrame.setVisible(true);
    }
}  
