
package ReportingAndAnalytics;

 /**
 * SalesController.java
 * Implements "Record Sales" use case (Sequence Diagram 1).
 * Actor: Staff
 */
public class SalesController {
    private Database database;
    private InventoryService inventoryService;

    public SalesController(Database database, InventoryService inventoryService) {
        this.database = database;
        this.inventoryService = inventoryService;
    }

    /**
     * 3. recordSales(bookId, qty)
     * Steps 4-20 of Sequence Diagram 1.
     * Returns a SaleResult with status and details for the GUI to display.
     */
    public SaleResult recordSales(String bookId, int qty) {
        // 4. getBookDetails(bookId) -> 5/6/7. Book details
        Book book = inventoryService.getBookDetails(bookId);
        if (book == null) {
            return new SaleResult(false, "Book not found", null, 0, false); // 8a
        }

        // 8. checkStock(bookId, qty) -> 9/10
        String stockStatus = inventoryService.checkStock(bookId, qty);
        if (stockStatus.equals("BOOK_NOT_FOUND")) {
            return new SaleResult(false, "Book not found", null, 0, false); // 8a
        }
        if (stockStatus.equals("INSUFFICIENT_STOCK")) {
            return new SaleResult(false, "Insufficient stock", null, 0, false); // 11a
        }

        // Calculate total price (step 4 of basic flow / "system calculates total price")
        double total = book.getPrice() * qty;

        // 12. saveSale(bookId, qty, total) -> 13. insertSale() -> 14. saleId
        String saleId = database.insertSale(bookId, qty, total);

        // 15. updateStock(bookId, qty) -> 16/17
        inventoryService.updateStock(bookId, qty);

        // 18. checkLowStock(bookId) -> 19/20
        boolean lowStockAlert = inventoryService.isLowStock(bookId);

        // 21. Sale recorded successfully and show success
        return new SaleResult(true, "Sale recorded successfully (Sale ID: " + saleId + ")",
                book, total, lowStockAlert);
    }

    /** Recalculate total when staff edits quantity (Alternative Flow) */
    public double recalculateTotal(String bookId, int qty) {
        Book book = inventoryService.getBookDetails(bookId);
        if (book == null) return 0;
        return book.getPrice() * qty;
    }

    /** Result wrapper class for Record Sales */
    public static class SaleResult {
        public boolean success;
        public String message;
        public Book book;
        public double total;
        public boolean lowStockAlert;

        public SaleResult(boolean success, String message, Book book, double total, boolean lowStockAlert) {
            this.success = success;
            this.message = message;
            this.book = book;
            this.total = total;
            this.lowStockAlert = lowStockAlert;
        }
    }
}   

