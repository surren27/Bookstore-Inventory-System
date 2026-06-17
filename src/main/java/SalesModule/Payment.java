/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public abstract class Payment {

    private double amount;
    private String paymentStatus;

    public Payment() {
    }

    public Payment(double amount,
            String paymentStatus) {

        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public abstract void processPayment();
}
