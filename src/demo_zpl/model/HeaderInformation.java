/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.model;

/**
 * @author davidgomez
 */
public class HeaderInformation {

    private final String storeName;
    private final String storeAddress;
    private final String storePhoneNumber;
    private final String storeOpenHour;
    private final String storeCloseHour;
    private final String storeOpenHourWeekend;
    private final String storeCloseHourWeekend;
    private final String trxNumber;
    private final String employeeId;
    private final String refNumber;
    private final String customerName;
    private final String customerAddress;
    private final String customerPhoneNumber;
    private final String customerRfc;

    public HeaderInformation(String storeName, String storeAddress, String storePhoneNumber, final String storeOpenHour, final String storeCloseHour, final String storeOpenHourWeekend, final String storeCloseHourWeekend, String trxNumber, String employeeId, String refNumber, String customerName, String customerAddress, String customerPhoneNumber, String customerRfc) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePhoneNumber = storePhoneNumber;
        this.storeOpenHour = storeOpenHour;
        this.storeCloseHour = storeCloseHour;
        this.storeOpenHourWeekend = storeOpenHourWeekend;
        this.storeCloseHourWeekend = storeCloseHourWeekend;
        this.trxNumber = trxNumber;
        this.employeeId = employeeId;
        this.refNumber = refNumber;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerRfc = customerRfc;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStorePhoneNumber() {
        return storePhoneNumber;
    }

    public String getTrxNumber() {
        return trxNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerRfc() {
        return customerRfc;
    }

    public String getStoreOpenHourWeekend() {
        return storeOpenHourWeekend;
    }

    public String getStoreCloseHourWeekend() {
        return storeCloseHourWeekend;
    }

    public String getStoreCloseHour() {
        return storeCloseHour;
    }

    public String getStoreOpenHour() {
        return storeOpenHour;
    }
}
