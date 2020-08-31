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
import java.nio.charset.StandardCharsets;

/**
 * @author davidgomez
 */
public class PrintLabel {

    private void sendTestToPrint(final String usbName, final String ipAddress, final int port, final String option) {
        if (option.equals(OptionConnect.USB)) {
            this.sendLabelToPrintUSB(usbName, getTestLabel());
        } else {
            this.sendLabelToPrintIP(ipAddress, port, getTestLabel());
        }
    }

    private void sendFileToPrinter(final String filePath) {
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

    public Boolean sendLabelToPrintIP(final String ipAddress, final int port, final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionIP(ipAddress, port);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            printer.sendCommand(zebraLabel);
        } catch (ConnectionException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (ConnectionException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Boolean sendLabelToPrintUSB(final String usbName, final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionUSB(usbName);
            printerConnection.open();
            printerConnection.write(zebraLabel.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (printerConnection != null) {
                    printerConnection.close();
                }
            } catch (ConnectionException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
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
