/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.utils;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.discovery.DiscoveredPrinterDriver;
import com.zebra.sdk.printer.discovery.UsbDiscoverer;
import demo_zpl.enums.OptionConnect;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import java.util.ArrayList;
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
        zebraElements.add(0, new ZebraNativeZpl("^CI28"));
        zebraLabel.setZebraElements(zebraElements);
        return zebraLabel.getZplCode();
    }

    /**
     * @param fontSize
     * @return array[height,width] in dots
     */
    public static Integer[] extractDotsFromFont(final int fontSize) {
        Integer[] array = new Integer[2];
        //We use ratio to converted (based on ratio used by Zebra Designer Tools)
        array[0] = Math.round(fontSize * 4.16F);//Heigth
        array[1] = Math.round(fontSize * 4.06F);//With
        return array;
    }

    public static List<DiscoveredPrinterDriver> getListPrinterByFilter(final OptionConnect option) {
        final List<DiscoveredPrinterDriver> printerDriverList = new ArrayList<>();
        try {
            if (option.equals(OptionConnect.USB_CONNECT)) {
                for (DiscoveredPrinterDriver printer : UsbDiscoverer.getZebraDriverPrinters()) {
                    printerDriverList.add(printer);
                }
            } else {
                //NetworkDiscoverer.localBroadcast(new DiscoveryHandlerImpl(discoverButton));
            }
        } catch (ConnectionException ex) {
            return printerDriverList;
        }
        return printerDriverList;
    }
}
