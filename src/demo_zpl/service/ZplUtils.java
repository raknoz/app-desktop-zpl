/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

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
public class ZplUtils {

    /**
     * Method to encapsulate the creation of template in ZPL code.
     *
     * @return A String with the template in ZPL Code.
     */
    static String generateZplCode(final int widthPage, final int spaceLine,
            final ZebraFont zebraFont, final List<ZebraElement> zebraElements, final int currentPositionLine) {
        final ZebraLabel zebraLabel = new ZebraLabel(widthPage, currentPositionLine + spaceLine);
        zebraLabel.setDefaultZebraFont(zebraFont);
        //Add character to set utf-8 encoding.
        zebraElements.add(0, new ZebraNativeZpl("^CI28"));
        //Add the list of the rest of the elements.
        zebraLabel.setZebraElements(zebraElements);
        return zebraLabel.getZplCode();
    }

}
