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
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.PrinterOptions;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraAFontElement;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import java.util.List;

/**
 *
 * @author davidgomez
 */
public class ZplCustomUtils {

    /**
     * Method to encapsulate the creation of template in ZPL code.
     *
     * @return A String with the template in ZPL Code.
     */
    public static String generateZplCode(final int widthPage, final int spaceLine, final List<ZebraElement> zebraElements,
                                   final int currentPositionLine) {
        final ZebraLabel zebraLabel = new ZebraLabel(widthPage, currentPositionLine + spaceLine);
        //Add character to set utf-8 encoding.
        zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);
        zebraElements.add(0, new ZebraNativeZpl("^CI27"));
        zebraLabel.setZebraElements(zebraElements);
        return zebraLabel.getZplCode();//.replaceAll("\\^A0N,25,24", "\\^AFN,10,5");
    }

    public static void printLabel(final String zplLabel) throws ConnectionException {
        Connection printerConnection = ConnectionUtil.getConnection("127.0.0.1", 9100);
        printerConnection.open();
        final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
        printer.sendCommand(zplLabel);
        printerConnection.close();
    }
    
    public static void printLabelUSB(final String zplLabel) throws ConnectionException {
        Connection printerConnection = ConnectionUtil.getConnectionUSB();
        printerConnection.open();
        final ZebraPrinter printer = ZebraPrinterFactory.getInstance(PrinterLanguage.ZPL, printerConnection);
        printer.sendCommand(zplLabel);
        printerConnection.close();
    }

    /**
     * @return array[height,width] in dots
     */
    public static Integer[] extractDotsFromFont(final int fontSize) {
        Integer[] array = new Integer[2];
        //We use ratio to converted (based on ratio used by Zebra Designer Tools)
        array[0] = Math.round(fontSize * 4.16F);//Heigth
        array[1] = Math.round(fontSize * 4.06F);//With
        return array;
    }
}
