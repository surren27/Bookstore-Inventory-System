package bookstore.model;

public class Product {

    private int productID;

    private String title;
    private String author;
    private String isbn;
    private String category;
    private double price;

    // Composition Relationship
    private Inventory inventory;

    // Constructor for ADD PRODUCT
    public Product(String title,
               String author,
               String isbn,
               String category,
               double price,
               Inventory inventory) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
    }

    // Constructor for UPDATE PRODUCT
    public Product(int productID,
                   String title,
                   String author,
                   String category,
                   double price,
                   Inventory inventory) {

        this.productID = productID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.inventory = inventory;
    }

    public int getProductID() {
        return productID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {

    return isbn;
}

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public Inventory getInventory() {
        return inventory;
    }
}