/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class PaymentFactory {

    public static Payment createPayment(
            String paymentType) {

        if(paymentType.equals("Card")) {

            return new CardPayment();
        }

        else if(paymentType.equals("Online Banking")) {

            return new OnlineBankingPayment();
        }

        return null;
    }
}
