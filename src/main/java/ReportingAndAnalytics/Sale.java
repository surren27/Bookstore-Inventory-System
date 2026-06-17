
package ReportingAndAnalytics;

import java.time.LocalDate;

import java.time.LocalDate;
public class Sale {

/**
 * Sale.java
 * Represents a sales transaction record.
 */
    private String saleId;
    private String bookId;
    private int quantity;
    private double total;
    private LocalDate date;

    public Sale(String saleId, String bookId, int quantity, double total, LocalDate date) {
        this.saleId = saleId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    public String getSaleId() { return saleId; }
    public String getBookId() { return bookId; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return total; }
    public LocalDate getDate() { return date; }
}    

