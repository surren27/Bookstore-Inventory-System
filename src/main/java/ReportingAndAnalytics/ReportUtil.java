
package ReportingAndAnalytics;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ReportUtil.java
 * Shared helper calculations used by Report Service / Analytics Service.
 */
public class ReportUtil {

    /** Group sales totals by book title (used by ChartService for Sales Report chart) */
    public static Map<String, Double> totalByBook(List<Sale> sales, Database database) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Sale s : sales) {
            Book b = database.getBook(s.getBookId());
            String name = (b != null) ? b.getTitle() : s.getBookId();
            result.merge(name, s.getTotal(), Double::sum);
        }
        return result;
    }

    /** Sum of total revenue for a list of sales */
    public static double sumRevenue(List<Sale> sales) {
        double total = 0;
        for (Sale s : sales) {
            total += s.getTotal();
        }
        return total;
    }
}    

