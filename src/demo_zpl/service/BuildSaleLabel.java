/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import demo_zpl.model.BodyLayawayInformation;
import demo_zpl.model.HeaderInformation;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import fr.w3blog.zpl.model.element.ZebraText;

import java.time.LocalDateTime;

/**
 * @author davidgomez
 */
public class BuildSaleLabel {

    private static final int LOGO_MARGIN_TOP = 70;
    //private static final int HEADER_MARGIN_TOP = 280;
    private static final int DEFAULT_MARGIN_LEFT = 25;
    private static final int DEFAULT_FONT_SIZE = 5;
    private static final int FONT_SIZE_CONDITIONS = 4;
    private static final ZebraFont DEFAULT_FONT = ZebraFont.ZEBRA_ZERO;
    private static final int SPACE_LINE = 24;
    //private static final int CONDITIONS_STARTER_LINE = 370;
    //private static final int BODY_STARTER_LINE = 1009;
    //private static final int FOOTER_ROW_LINE = 1035;
    private static final int WIDTH_PAGE = 609;
    private static final int HEIGHT_PAGE = 5000; //62,8 cm

    public String generateCustomLabel() {

        //Setup
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodyLayawayInformation bodyLayawayInformation = MockFactory.getBodyInformation();
        final ZebraLabel zebraLabel = new ZebraLabel(WIDTH_PAGE, HEIGHT_PAGE);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setDefaultFontSize(DEFAULT_FONT_SIZE);

        //Logo
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, LOGO_MARGIN_TOP, "LOGO EMPRESA"));

        //Title
        zebraLabel.addElement(new ZebraNativeZpl("^FT225,70^A0N,28,28^FB118,1,0^FH^FDVenta^FS"));
        zebraLabel.addElement(new ZebraText(225, LOGO_MARGIN_TOP + SPACE_LINE * 2, "Venta"));

        //Information of store and the transaction
        // Position X - Y - TEXT - Font
        int headerMarginTop = LOGO_MARGIN_TOP + SPACE_LINE * 2;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop, headerInformation.getStoreName()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + SPACE_LINE, headerInformation.getStoreAddress()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (2 * SPACE_LINE), headerInformation.getStorePhoneNumber()));

        //Information of customer
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop, headerInformation.getCustomerName()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + SPACE_LINE, headerInformation.getCustomerAddress()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (2 * SPACE_LINE), headerInformation.getCustomerPhoneNumber()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (3 * SPACE_LINE), headerInformation.getCustomerRfc()));

        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (4 * SPACE_LINE), "No. Transacci\\A2n:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (5 * SPACE_LINE), "Id. Empleado:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (6 * SPACE_LINE), "Fecha:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, headerMarginTop + (7 * SPACE_LINE), "Referencia #:"));

        zebraLabel.addElement(new ZebraText(230, headerMarginTop + (4 * SPACE_LINE), headerInformation.getTrxNumber()));
        zebraLabel.addElement(new ZebraText(230, headerMarginTop + (5 * SPACE_LINE), headerInformation.getEmployeeId()));
        zebraLabel.addElement(new ZebraText(230, headerMarginTop + (6 * SPACE_LINE), LocalDateTime.now().toString()));
        zebraLabel.addElement(new ZebraText(230, headerMarginTop + (7 * SPACE_LINE), headerInformation.getRefNumber()));

        return zebraLabel.getZplCode();
    }


}
