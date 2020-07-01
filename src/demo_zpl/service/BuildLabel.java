/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import fr.w3blog.zpl.model.element.ZebraText;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import demo_zpl.model.BodyPaymentInformation;
import demo_zpl.model.HeaderInformation;

import static demo_zpl.service.MockFactory.getHeaderInformation;

/**
 *
 * @author davidgomez
 */
public class ManualLabel {

    final int ROW_INIT = 306;
    final int COLUMN_INIT = 25;
    final int DEFAULT_FONT_SIZE = 5;
    final ZebraFont DEFAULT_FONT = ZebraFont.ZEBRA_ZERO;
    final int SPACE_LINE = 24;

    public String generateHeaderLabel() {

        //Width and Height
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final ZebraLabel zebraLabel = new ZebraLabel(609, 1015);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setDefaultFontSize(DEFAULT_FONT_SIZE);
        
        zebraLabel.addElement(new ZebraNativeZpl(
                "^LS0^FS"
                + "^FO190,32^GFA,07168,07168,00056,:Z64:^FS"
                + "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:8BE4"
                /* End image */
                + "^FT225,228^A0N,28,28^FB118,1,0^FH^FDApartados^FS"));

        //Information of store and the transaction
        // Position X - Y - TEXT - Font
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (0 * SPACE_LINE), headerInformation.getStoreName()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (1 * SPACE_LINE), headerInformation.getStoreAddress()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (2 * SPACE_LINE), headerInformation.getStorePhoneNumber()));

        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (4 * SPACE_LINE), "No. Transacci\\A2n:"));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (5 * SPACE_LINE), "Id. Empleado:"));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (6 * SPACE_LINE), "Fecha:"));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, ROW_INIT + (7 * SPACE_LINE), "Referencia #:"));

        zebraLabel.addElement(new ZebraText(170, ROW_INIT + (4 * SPACE_LINE), headerInformation.getTrxNumber()));
        zebraLabel.addElement(new ZebraText(170, ROW_INIT + (5 * SPACE_LINE), headerInformation.getEmployeeId()));
        zebraLabel.addElement(new ZebraText(170, ROW_INIT + (6 * SPACE_LINE), LocalDateTime.now().toString()));
        zebraLabel.addElement(new ZebraText(170, ROW_INIT + (7 * SPACE_LINE), headerInformation.getRefNumber()));

        //Conditions
        zebraLabel.addElement(new ZebraNativeZpl("^FT25,528^A0N,17,14^FB553,1,0^FH^FDLa exhibici\\A2n y comercializaci\\A2n de los art\\A1culos objeto de la presente operaci\\A2n, es realizada^FS"
                + "^FT25,549^A0N,17,14^FB553,1,0^FH^FDpor EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo con ^FS"
                + "^FT25,570^A0N,17,14^FB553,1,0^FH^FDInterese y Garant\\A1a Prendaria y al art\\A1culo 9 fracci\\A2n IV de la ley del impuesto al Valor^FS"
                + "^FT25,591^A0N,17,14^FB553,1,0^FH^FDAgregado, por lo que al ser productos usados, no se otorga garant\\A1a alguna conforme lo ^FS"
                + "^FT25,612^A0N,17,14^FB553,1,0^FH^FDdispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el Cliente verificar su^FS"
                + "^FT25,633^A0N,17,14^FB553,1,0^FH^FDcondici\\A2n y funcionamiento antes de salir del establecimiento, ya que las ventas de los^FS"
                + "^FT25,654^A0N,17,14^FB553,1,0^FH^FDproductos son definitivas y no est\\A0n sujetas a cambio o devoluci\\A2n.^FS"));

        //Information of customer
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (0 * SPACE_LINE), headerInformation.getCustomerName()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (1 * SPACE_LINE), headerInformation.getCustomerAddress()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (2 * SPACE_LINE), headerInformation.getCustomerPhoneNumber()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (3 * SPACE_LINE), headerInformation.getCustomerRfc()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (4 * SPACE_LINE), "Cantidad:"));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (5 * SPACE_LINE), "Precio:"));

        zebraLabel.addElement(new ZebraText(110, 817 + (4 * SPACE_LINE), headerInformation.getItemQty()));
        zebraLabel.addElement(new ZebraText(110, 817 + (5 * SPACE_LINE), headerInformation.getItemPrice()));
        zebraLabel.addElement(new ZebraText(COLUMN_INIT, 817 + (7 * SPACE_LINE), "Fecha de pago:"));

        //Add Body
        int paymentLine = 1;
        for (BodyPaymentInformation bodyBodyPayment : MockFactory.getBodyPaymentsInformation()) {
            zebraLabel.addElement(new ZebraText(COLUMN_INIT, 985 + (paymentLine * SPACE_LINE), bodyBodyPayment.getPaymentDate()));
            zebraLabel.addElement(new ZebraText(COLUMN_INIT, 985 + (++paymentLine * SPACE_LINE), bodyBodyPayment.getPaymentAmount()));
            paymentLine *= 2;
        }

        //Add footer

        //Replace the end of the template to add more information.
        return zebraLabel.getZplCode();
    }
    
    

}
