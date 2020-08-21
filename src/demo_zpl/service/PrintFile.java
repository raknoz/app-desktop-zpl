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
import demo_zpl.utils.ConnectionUtil;

/**
 * @author davidgomez
 */
public class PrintFile {

    public void sendFileToPrinter() {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnection("127.0.0.1", 9100);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            String filePath = getFilePath();
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

    public void sendLabelToPrintIP(final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnection("127.0.0.1", 9100);
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

    public void sendLabelToPrintUSB(final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = ConnectionUtil.getConnectionUSB();
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

    private String getFilePath() {
        return "classpath:";
    }
}
