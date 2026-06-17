
package ReportingAndAnalytics;



    
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * AnalyticsService.java
 * Supports "View Sales Trend Analytics" use case (Sequence Diagram 7).
 * Actor: Manager
 */
public class AnalyticsService {
    private Database database;

    public AnalyticsService(Database database) {
        this.database = database;
    }

    /**
     * 4. getHistoricalSales(interval) -> 5. historical data
     * 6. organizeByTime(interval)
     * Returns time-ordered map of date/period -> total sales, or null if insufficient (9a)
     */
    public Map<String, Double> organizeByTime(String interval) {
        // 4-5. getHistoricalSales -> historical data
        List<Sale> historicalData = database.getHistoricalSales();

        if (historicalData == null || historicalData.size() < 2) {
            return null; // 9a. "Not enough data for trend"
        }

        // 6. organizeByTime(interval) - Daily or Monthly
        TreeMap<String, Double> grouped = new TreeMap<>();
        DateTimeFormatter formatter = interval.equalsIgnoreCase("Monthly")
                ? DateTimeFormatter.ofPattern("yyyy-MM")
                : DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Sale s : historicalData) {
            String key = s.getDate().format(formatter);
            grouped.merge(key, s.getTotal(), Double::sum);
        }

        return new LinkedHashMap<>(grouped);
    }
}
