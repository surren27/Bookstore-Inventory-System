
package ReportingAndAnalytics;



import java.time.LocalDate;
import java.util.List;

/**
 * FinancialService.java
 * Supports "Generate Financial Report" use case (Sequence Diagram 6).
 * Actor: Manager
 */
public class FinancialService {
    private Database database;

    public FinancialService(Database database) {
        this.database = database;
    }

    /**
     * 3. getFinancialData(period)
     * Steps 4-9 of Sequence Diagram 6.
     * Returns FinancialResult, or null on calculation error (10a).
     */
    public FinancialResult getFinancialData(LocalDate from, LocalDate to) {
        try {
            // 2. (Optional) select time period - default to current month if null
            if (from == null || to == null) {
                LocalDate now = LocalDate.now();
                from = now.withDayOfMonth(1);
                to = now;
            }

            // 4. getSalesData(period) -> 5. sales data
            List<Sale> salesData = database.querySalesData(from, to);

            // 8. calculateRevenue()
            double revenue = ReportUtil.sumRevenue(salesData);

            // 6. getCostData(period) -> 7. cost data
            double cost;
            boolean costMissing = false;
            try {
                cost = database.getCostData(from, to);
                if (cost < 0) {
                    costMissing = true;
                    cost = 0;
                }
            } catch (Exception ex) {
                costMissing = true; // 6a. Calculate Revenue Only
                cost = 0;
            }

            // 9. calculateProfit()
            double profit = costMissing ? 0 : (revenue - cost);

            return new FinancialResult(revenue, cost, profit, costMissing, from, to);
        } catch (Exception e) {
            return null; // 10a. "Calculation failed"
        }
    }

    /** Result wrapper class for Generate Financial Report */
    public static class FinancialResult {
        public double revenue;
        public double cost;
        public double profit;
        public boolean costMissing;
        public LocalDate from;
        public LocalDate to;

        public FinancialResult(double revenue, double cost, double profit, boolean costMissing,
                                LocalDate from, LocalDate to) {
            this.revenue = revenue;
            this.cost = cost;
            this.profit = profit;
            this.costMissing = costMissing;
            this.from = from;
            this.to = to;
        }
    }
}   

