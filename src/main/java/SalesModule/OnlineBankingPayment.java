/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SalesModule;

/**
 *
 * @author user
 */
public class OnlineBankingPayment extends Payment {

    private String bankName;

    public OnlineBankingPayment() {
    }

    public OnlineBankingPayment(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public void processPayment() {
        System.out.println("Online Banking Payment Successful");
    }
}
