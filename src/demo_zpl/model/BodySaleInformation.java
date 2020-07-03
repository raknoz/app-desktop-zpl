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
public class BodySaleInformation {

    private final List<Item> items;

    public BodySaleInformation(final List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public static class Item {
        private final String ezId;
        private final String fullDescription;
        private final int quantity;
        private final Double unitPrice;

        public Item(final String ezId, final String fullDescription, final int quantity, final Double unitPrice) {
            this.ezId = ezId;
            this.fullDescription = fullDescription;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getEzId() {
            return ezId;
        }

        public String getFullDescription() {
            return fullDescription;
        }

        public int getQuantity() {
            return quantity;
        }

        public Double getUnitPrice() {
            return unitPrice;
        }
    }
}
