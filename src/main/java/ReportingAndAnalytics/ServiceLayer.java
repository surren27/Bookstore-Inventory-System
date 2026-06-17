
package ReportingAndAnalytics;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ServiceLayer.java
 * Supports "Dashboard Overview" use case (Sequence Diagram 8).
 * Actors: Staff & Manager
 */
public class ServiceLayer {
    private Database database;
    private ChartService chartService;

    public ServiceLayer(Database database, ChartService chartService) {
        this.database = database;
        this.chartService = chartService;
    }

    /**
     * 3. getSummaryData()
     * Steps 4-11 of Sequence Diagram 8.
     * Returns DashboardData, or null on failure (12a).
     */
    public DashboardData getSummaryData() {

try {

    // TOTAL SALES
    double totalSales =
            ReportUtil.sumRevenue(
                    database.getAllSales()
            );

    // LOW STOCK BOOKS
    List<Book> lowStockBooks =
            new java.util.ArrayList<>();

    for(Book b : database.getAllBooks()) {

        if(b.getStock() < b.getMinStock()) {

            lowStockBooks.add(b);
        }
    }

    // BEST SELLING BOOKS
    Map<String, Double> topBestSellers =
            new LinkedHashMap<>();

    List<Book> books =
            database.getAllBooks();

    books.sort((a, b) ->
            Integer.compare(
                    a.getStock(),
                    b.getStock()
            )
    );

    int count = 0;

    for(Book b : books) {

        if(count >= 3) {

            break;
        }

        int estimatedSold =
                20 - b.getStock();

        if(estimatedSold < 0) {

            estimatedSold = 0;
        }

        topBestSellers.put(
                b.getTitle(),
                (double) estimatedSold
        );

        count++;
    }

    // CHART DATA
    Map<String, Double> charts =
            topBestSellers;

    return new DashboardData(
            totalSales,
            lowStockBooks,
            topBestSellers,
            charts
    );

} catch(Exception e) {

    e.printStackTrace();

    return null;
}


}


    /** Result wrapper class for Dashboard Overview */
    public static class DashboardData {
        public double totalSales;
        public List<Book> lowStockBooks;
        public Map<String, Double> topBestSellers;
        public Map<String, Double> charts;

        public DashboardData(double totalSales, List<Book> lowStockBooks,
                              Map<String, Double> topBestSellers, Map<String, Double> charts) {
            this.totalSales = totalSales;
            this.lowStockBooks = lowStockBooks;
            this.topBestSellers = topBestSellers;
            this.charts = charts;
        }
    }
}   
