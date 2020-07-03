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

    private String storeName;
    private String storeAddress;
    private String storePhoneNumber;
    private String trxNumber;
    private String employeeId;
    private String refNumber;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerRfc;

    public HeaderInformation(String storeName, String storeAddress, String storePhoneNumber, String trxNumber, String employeeId, String refNumber, String customerName, String customerAddress, String customerPhoneNumber, String customerRfc) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePhoneNumber = storePhoneNumber;
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
}
