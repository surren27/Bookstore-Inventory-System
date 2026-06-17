
package ReportingAndAnalytics;

import java.util.List;
import java.util.Map;

/**
 * ChartService.java
 * Simulates chart generation referenced in Sequence Diagrams 2 and 7
 * (generateChart(), generateLineChart()).
 * In this Swing implementation, "chart" objects are simple data holders
 * that the GUI layer renders using basic drawing (bar/line panels).
 */
public class ChartService {

    /** 8. generateChart() for Sales Report - returns chart data (category totals) */
    public Map<String, Double> generateChart(List<Sale> salesData, Database database) {
        return ReportUtil.totalByBook(salesData, database);
    }

    /** 7. generateLineChart() for Sales Trend Analytics - returns time-ordered totals */
    public Map<String, Double> generateLineChart(Map<String, Double> organizedData) {
        // Chart object is simply the organized data, ready for line rendering
        return organizedData;
    }
}    

