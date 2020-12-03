/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.dto;

import demo_zpl.enums.OptionConnect;

/**
 *
 * @author davidgomez
 */
public class DataToPrintDTO {

    private final String ipAddress;
    private final Integer port;
    private final String usbIdName;
    private final OptionConnect typeConnection;
    private final String textToBePrinted;

    public DataToPrintDTO(final String ipAddress, final Integer port, final String usbIdName,
            final OptionConnect typeConnection, final String textToBePrinted) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.usbIdName = usbIdName;
        this.typeConnection = typeConnection;
        this.textToBePrinted = textToBePrinted;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsbIdName() {
        return usbIdName;
    }

    public OptionConnect getTypeConnection() {
        return typeConnection;
    }

    public String getTextToBePrinted() {
        return textToBePrinted;
    }

}
