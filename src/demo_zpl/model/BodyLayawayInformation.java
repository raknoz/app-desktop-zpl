/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.model;

import java.util.List;

/**
 * @author davidgomez
 */
public class BodyLayawayInformation {

    private final List<Payment> payments;
    private final List<PendingPayment> pendingPayments;

    public BodyLayawayInformation(List<Payment> payments, List<PendingPayment> pendingPayments) {
        this.payments = payments;
        this.pendingPayments = pendingPayments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<PendingPayment> getPendingPayments() {
        return pendingPayments;
    }

    public static class Payment {

        private final String date;
        private final String amount;

        public Payment(String date, String amount) {
            this.date = date;
            this.amount = amount;
        }

        public String getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }
    }

    public static class PendingPayment extends Payment {
        public PendingPayment(String date, String amount) {
            super(date, amount);
        }
    }


}
