/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class SalesController {
    public void confirmOrder(Order order) {

        order.setOrderStatus("Confirmed");

        order.confirmOrder();
    }
    
}
