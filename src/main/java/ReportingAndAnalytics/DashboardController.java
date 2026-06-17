
package ReportingAndAnalytics;



/**
 * DashboardController.java
 * Implements "Dashboard Overview" use case (Sequence Diagram 8).
 * Actors: Staff & Manager
 */
public class DashboardController {
    private ServiceLayer serviceLayer;

    public DashboardController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    /**
     * 2. loadDashboard()
     * Steps 3-12 of Sequence Diagram 8.
     * Returns dashboard data, or null on failure (12a).
     */
    public ServiceLayer.DashboardData loadDashboard() {
        return serviceLayer.getSummaryData(); // 3. getSummaryData()
    }
}    

