
package ReportingAndAnalytics;
import java.util.ArrayList;
import java.util.List;
public class InventoryService {


/**
 * InventoryService.java
 * Handles stock checking, updating, and inventory report logic
 * as shown in sequence diagrams 1 (Record Sales) and 3 (Generate Inventory Report).
 */

    private Database database;

    public InventoryService(Database database) {
        this.database = database;
    }

    /** 4. getBookDetails(bookId) -> 5/6 via Database */
    public Book getBookDetails(String bookId) {
        return database.getBook(bookId);
    }

    /** 8. checkStock(bookId, qty) -> 9/10 via Database */
    public String checkStock(String bookId, int qty) {
        Book book = database.getBook(bookId);
        if (book == null) {
            return "BOOK_NOT_FOUND"; // 8a
        }
        if (database.getStock(bookId) < qty) {
            return "INSUFFICIENT_STOCK"; // 11a
        }
        return "OK";
    }

    /** 15. updateStock(bookId, qty) -> 16/17 via Database */
    public boolean updateStock(String bookId, int qty) {
        return database.updateStock(bookId, qty);
    }

    /** 18. checkLowStock(bookId) -> 19/20 via Database */
    public boolean isLowStock(String bookId) {
        Book b = database.getBook(bookId);
        if (b == null) return false;
        int minStock = database.getMinStock(bookId);
        return b.getStock() < minStock;
    }

    /**
     * 4. getStockData(filter) -> 5. queryStockData() -> 6. stock data
     * 7. compareWithThreshold() -> 8. labelItems(Low/Normal)
     * Returns map-like list of [Book, label]
     */
    public List<Object[]> getInventoryReport(String filterCategory) {
        List<Object[]> report = new ArrayList<>();
        List<Book> stockData = database.queryStockData(); // 5/6
        if (stockData == null || stockData.isEmpty()) {
            return null; // 9a Inventory data not available
        }
        for (Book b : stockData) {
            // 2a. use all items if no filter applied
            if (filterCategory == null || filterCategory.isEmpty()
                    || b.getCategory().equalsIgnoreCase(filterCategory)) {
                String label = (b.getStock() < b.getMinStock()) ? "Low" : "Normal"; // 7-8
                report.add(new Object[]{b, label});
            }
        }
        return report;
    } 
}
