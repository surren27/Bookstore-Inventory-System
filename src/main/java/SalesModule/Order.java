/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class Order {
   

    private String orderStatus;
    private String paymentMethod;
    private double totalAmount;

    public Order() {
    }

    public Order(String orderStatus,
            String paymentMethod,
            double totalAmount) {

        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void checkout() {
        System.out.println("Checkout Successful");
    }

    public void confirmOrder() {
        System.out.println("Order Confirmed");
    }
    
}
