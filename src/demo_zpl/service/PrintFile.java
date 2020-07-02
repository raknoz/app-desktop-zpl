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

import java.util.HashMap;
import java.util.Map;

/**
 * @author davidgomez
 */
public class PrintFile {

    public void sendFileToPrinter(final ConnectionUtil connectionUtil) {
        Connection printerConnection = null;
        try {
            printerConnection = connectionUtil.getConnection("127.0.0.1", 9100);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            //String filePath = createDemoFile(printer.getPrinterControlLanguage());
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
            }
        }
    }

    public void sendFileToPrinter2(final ConnectionUtil connectionUtil) {
        Connection printerConnection = null;
        try {
            printerConnection = connectionUtil.getConnection("127.0.0.1", 9100);
            printerConnection.open();
            ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            Map<Integer, String> vars = new HashMap<>();
            vars.put(2, "El Store");
            vars.put(3, "La direciión");
            vars.put(8, "AAD841185");
            printer.printStoredFormat(getFilePath(), vars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToPrint(final ConnectionUtil connectionUtil, final String zebraLabel) {
        Connection printerConnection = null;
        try {
            printerConnection = connectionUtil.getConnection("127.0.0.1", 9100);
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            printer.sendCommand(zebraLabel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendToPrintUSB(final ConnectionUtil connectionUtil, final String zebraLabel) {
        Connection printerConnection = null;
        try {

            printerConnection = connectionUtil.getConnectionUSB();
            printerConnection.open();
            final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
            printer.sendCommand(zebraLabel);
            //printerConnection.sendAndWaitForResponse(zebraLabel.getBytes(), 5000, 500, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getFilePath() {
        return "/home/davidgomez/Downloads/zebra_print/file_test/header.zpl";
    }
}
