package ReportingAndAnalytics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportController {


private Database database;
private ChartService chartService;

public ReportController(
        Database database,
        ChartService chartService) {

    this.database = database;
    this.chartService = chartService;
}

/**
 * Generate Sales Report
 */
public SalesReportResult generateSalesReport(
        LocalDate from,
        LocalDate to) {

    try {

        if(from == null || to == null) {

            LocalDate now =
                    LocalDate.now();

            from =
                    now.withDayOfMonth(1);

            to = now;
        }

        List<Sale> salesData =
                database.querySalesData(
                        from,
                        to
                );

        double totalRevenue =
                ReportUtil.sumRevenue(
                        salesData
                );

        int totalTransactions =
                salesData.size();

        Map<String, Double> chart =
                chartService.generateChart(
                        salesData,
                        database
                );

        return new SalesReportResult(
                totalRevenue,
                totalTransactions,
                chart,
                from,
                to
        );

    } catch(Exception e) {

        e.printStackTrace();

        return null;
    }
}

/**
 * View Best Selling Books
 */
public List<Object[]> getBestSellingBooks(
        String criteria) {

    List<Book> books =
            database.getAllBooks();

    if(books == null || books.isEmpty()) {

        return null;
    }

    // Lowest stock = best selling
    books.sort((a, b) ->
            Integer.compare(
                    a.getStock(),
                    b.getStock()
            )
    );

    List<Object[]> ranking =
            new ArrayList<>();

    int maxStock = 20;

    for(Book b : books) {

        int estimatedSold =
                maxStock - b.getStock();

        if(estimatedSold < 0) {

            estimatedSold = 0;
        }

        double value;

        if(criteria.equalsIgnoreCase(
                "Revenue")) {

            value =
                    estimatedSold
                    * b.getPrice();

        } else {

            value = estimatedSold;
        }

        ranking.add(
                new Object[]{
                        b,
                        value
                }
        );
    }

    ranking.sort((a, b) ->
            Double.compare(
                    (double) b[1],
                    (double) a[1]
            )
    );

    if(ranking.size() > 10) {

        ranking =
                ranking.subList(0, 10);
    }

    return ranking;
}

/**
 * View Low Sales Books
 */
public List<Object[]> getLowSales(
        int threshold) {

    List<Book> books =
            database.getAllBooks();

    List<Object[]> result =
            new ArrayList<>();

    int maxStock = 20;

    for(Book b : books) {

        int estimatedSold =
                maxStock - b.getStock();

        if(estimatedSold < threshold) {

            result.add(
                    new Object[]{
                            b,
                            estimatedSold
                    }
            );
        }
    }

    return result;
}

/**
 * Sales Report Result Wrapper
 */
public static class SalesReportResult {

    public double totalRevenue;
    public int totalTransactions;
    public Map<String, Double> chart;
    public LocalDate from;
    public LocalDate to;

    public SalesReportResult(
            double totalRevenue,
            int totalTransactions,
            Map<String, Double> chart,
            LocalDate from,
            LocalDate to) {

        this.totalRevenue =
                totalRevenue;

        this.totalTransactions =
                totalTransactions;

        this.chart = chart;

        this.from = from;

        this.to = to;
    }
}


}
