/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import demo_zpl.enums.OptionConnect;
import demo_zpl.utils.ConnectionUtil;

/**
 * @author davidgomez
 */
public class PrintLabel {
    
    public void sendTestToPrint(final String usbName, final String ipAddress, final int port, final String option) {
        if (option.equals(OptionConnect.USB)) {
            this.sendLabelToPrintUSB(usbName, getTestLabel());
        } else {
            this.sendLabelToPrintIP(ipAddress, port, getTestLabel());
        }
    }

    public void sendFileToPrinter(final String filePath) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionIP("127.0.0.1", 9100);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            printer.sendFileContents(filePath);
        } catch (ConnectionException e) {
            e.printStackTrace();
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendLabelToPrintIP(final String ipAddress, final int port, final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionIP(ipAddress, port);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            printer.sendCommand(zebraLabel);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendLabelToPrintUSB(final String usbName, final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionUSB(usbName);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            printer.sendCommand(zebraLabel);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
        }
    }
    
   
    /*
     * Returns the command for a test label depending on the printer control language
     * The test label is a box with the word "TEST" inside of it
     * 
     * _________________________
     * |                       |
     * |                       |
     * |        TEST           |
     * |                       |
     * |                       |
     * |_______________________|
     * 
     * 
     */
     private String getTestLabel() {
        return "^XA^FO17,16^GB379,371,8^FS^FT65,255^A0N,135,134^FDTEST^FS^XZ";
    }
}
