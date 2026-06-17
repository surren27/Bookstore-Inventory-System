/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class Customer extends User {
    private String customerName;
    private String email;

    public Customer() {
    }

    public Customer(String customerName, String email) {
        this.customerName = customerName;
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void browseBook() {
        System.out.println("Browsing Books");
    }

    public void addToCart() {
        System.out.println("Book Added");
    }

    public void checkout() {
        System.out.println("Checkout Successful");
    }
    
    
}
