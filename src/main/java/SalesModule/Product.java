/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class Product {
    private String productID;
    private String title;
    private String author;
    private double price;
    private String isbn;
    private int stockQuantity;

    public Product() {
    }

    public Product(String productID, String title,
            String author, double price) {

        this.productID = productID;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    

    public void displayProduct() {
        System.out.println(title);
    }
}
