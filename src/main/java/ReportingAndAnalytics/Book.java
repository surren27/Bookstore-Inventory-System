
package ReportingAndAnalytics;
public class Book {

/**
 * Book.java
 * Represents a book record in the inventory.
 */
    private String bookId;
    private String title;
    private double price;
    private int stock;
    private int minStock;
    private String category;
    private String supplier;

    public Book(String bookId, String title, double price, int stock, int minStock, String category, String supplier) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.minStock = minStock;
        this.category = category;
        this.supplier = supplier;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getMinStock() { return minStock; }
    public String getCategory() { return category; }
    public String getSupplier() { return supplier; }

    @Override
    public String toString() {
        return bookId + " - " + title;
    }
}
    

    
