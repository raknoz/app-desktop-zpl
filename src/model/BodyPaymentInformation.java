/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author davidgomez
 */
public class BodyPaymentInformation {
    
    private final String paymentDate;
    private final String paymentAmount;

    public BodyPaymentInformation(String paymentDate, String paymentAmount) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }
        
}
