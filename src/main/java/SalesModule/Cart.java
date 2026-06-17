/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class Cart {

    private Product[] product;
    private int quantity;
    private double totalAmount;
    private int itemCount;

    public Cart() {

        product = new Product[10];
        itemCount = 0;
    }

    public Cart(Product[] product,
            int quantity,
            double totalAmount) {

        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Product[] getProduct() {
        return product;
    }

    public void setProduct(Product[] product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void addItem(Product p) {

        if(itemCount < product.length) {

            product[itemCount] = p;
            itemCount++;

            System.out.println("Item Added");
        }
    }

    public void removeItem() {

        System.out.println("Item Removed");
    }

    public double calculateTotal() {

        return totalAmount;
    }
}
