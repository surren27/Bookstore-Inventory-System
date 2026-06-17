
package ReportingAndAnalytics;


 import java.util.Map;

/**
 * AnalyticsController.java
 * Implements "View Sales Trend Analytics" use case (Sequence Diagram 7).
 * Actor: Manager
 */
public class AnalyticsController {
    private AnalyticsService analyticsService;
    private ChartService chartService;

    public AnalyticsController(AnalyticsService analyticsService, ChartService chartService) {
        this.analyticsService = analyticsService;
        this.chartService = chartService;
    }

    /**
     * 3. getSalesTrend(interval)
     * Steps 4-9 of Sequence Diagram 7.
     * Returns chart data (trend graph), or null if insufficient data (9a).
     */
    public Map<String, Double> getSalesTrend(String interval) {
        // 4-6. getHistoricalSales -> organizeByTime
        Map<String, Double> organized = analyticsService.organizeByTime(interval);
        if (organized == null) {
            return null; // 9a. "Not enough data for trend"
        }
        // 7-8. generateLineChart() -> chart
        return chartService.generateLineChart(organized); // 9. trend graph
    }
}   

