package bookstore.model;

public class Inventory {

    private int stockQuantity;
    private int reorderLevel;

    public Inventory(int stockQuantity,
                     int reorderLevel) {

        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
    }

    public boolean isLowStock() {

        return stockQuantity <= reorderLevel;
    }

    public void reduceStock(int quantity) {

        stockQuantity -= quantity;
    }

    public void increaseStock(int quantity) {

        stockQuantity += quantity;
    }

    public int getStockQuantity() {

        return stockQuantity;
    }

    public int getReorderLevel() {

        return reorderLevel;
    }
}
