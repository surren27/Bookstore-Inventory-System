
package ReportingAndAnalytics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bookstore.database.DatabaseManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Database.java
 * Simulates the Database component shown in the sequence diagrams.
 * Provides getBook, getStock, queryStockData, insertSale, updateStock,
 * querySalesData, getMore (reorder threshold), getCostData, getHistoricalSales.
 */
public class Database {
    private List<Book> books = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();
    private double totalCost = 0.0;
    private int saleCounter = 1000;

    public Database() {
        // Sample data
    }

    /** 5. getBook(bookId) */
    public Book getBook(String bookId) {

    List<Book> books = getAllBooks();

    for(Book b : books) {

        if(b.getBookId().equals(bookId)) {

            return b;
        }
    }

    return null;
}

    /** 9. getStock(bookId) */
    public int getStock(String bookId) {

    Book b = getBook(bookId);

    return (b != null)
            ? b.getStock()
            : -1;
}

    /** 16. updateStock() */
    public boolean updateStock(
        String bookId,
        int qty) {

    try {

        Connection conn =
                DatabaseManager.connectDB();

        String sql =
"UPDATE product SET stockQty = stockQty - ? WHERE productID = ?";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setInt(1, qty);

        pst.setInt(2,
                Integer.parseInt(bookId));

        int rows =
                pst.executeUpdate();

        conn.close();

        return rows > 0;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return false;
}

    /** 13. insertSale() -> 14. saleId */
    public String insertSale(String bookId, int qty, double total) {
        String saleId = "S" + (++saleCounter);
        sales.add(new Sale(saleId, bookId, qty, total, LocalDate.now()));
        return saleId;
    }

    /** 19. getMore(Stock,bookId) -> 20. minStock */
    public int getMinStock(String bookId) {
        Book b = getBook(bookId);
        return (b != null) ? b.getMinStock() : -1;
    }

    /** querySalesData() / getSalesData(from,to) */
    public List<Sale> querySalesData(LocalDate from, LocalDate to) {

    List<Sale> sales = new ArrayList<>();

    try {

        Connection conn =
                DatabaseManager.connectDB();

        String sql =
"SELECT * FROM payments WHERE payment_date BETWEEN ? AND ?";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setDate(1,
                java.sql.Date.valueOf(from));

        pst.setDate(2,
                java.sql.Date.valueOf(to));

        ResultSet rs =
                pst.executeQuery();

        int counter = 1;

        while(rs.next()) {

            Sale sale =
        new Sale(
                "S" + counter,
                String.valueOf(1),
                1,
                rs.getDouble("payment_amount"),
                rs.getDate("payment_date")
                        .toLocalDate()
        );

            sales.add(sale);

            counter++;
        }

        conn.close();

    } catch(Exception e) {

        e.printStackTrace();
    }

    return sales;
}

    /** queryStockData(filter) - returns all books, filter applied by service */
    public List<Book> queryStockData() {

    return getAllBooks();
}

    /** getCostData(period) */
  

    /** getHistoricalSales(interval) */
    public List<Sale> getHistoricalSales() {

    return querySalesData(
            LocalDate.of(2024,1,1),
            LocalDate.now()
    );
}
public double getCostData(
LocalDate from,
LocalDate to) {


double totalCost = 0;

try {

    Connection conn =
            DatabaseManager.connectDB();

    String sql =


"SELECT SUM(total_amount) AS totalCost FROM purchase_orders WHERE order_date BETWEEN ? AND ?";


    PreparedStatement pst =
            conn.prepareStatement(sql);

    pst.setDate(
            1,
            java.sql.Date.valueOf(from)
    );

    pst.setDate(
            2,
            java.sql.Date.valueOf(to)
    );

    ResultSet rs =
            pst.executeQuery();

    if(rs.next()) {

        totalCost =
                rs.getDouble("totalCost");
    }

    conn.close();

} catch(Exception e) {

    e.printStackTrace();
}

return totalCost;


}

    public List<Book> getAllBooks() {

    List<Book> books = new ArrayList<>();

    try {

        Connection conn =
                DatabaseManager.connectDB();

        String sql =
                "SELECT * FROM product";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while(rs.next()) {

            Book book =
                    new Book(
                            String.valueOf(rs.getInt("productID")),
                            rs.getString("title"),
                            rs.getDouble("price"),
                            rs.getInt("stockQty"),
                            5,
                            rs.getString("category"),
                            "Default Supplier"
                    );

            books.add(book);
        }

        conn.close();

    } catch(Exception e) {

        e.printStackTrace();
    }

    return books;
}

    public List<Sale> getAllSales() {

    return querySalesData(
            LocalDate.of(2024,1,1),
            LocalDate.now()
    );
}
}   

