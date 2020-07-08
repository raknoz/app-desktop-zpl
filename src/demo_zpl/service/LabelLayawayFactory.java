/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import demo_zpl.model.HeaderInformation;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.model.element.ZebraText;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidgomez
 */
public class LabelLayawayFactory {

    private static final int DEFAULT_MARGIN_LEFT = 25;
    private static final int DEFAULT_FONT_SIZE = 5;
    private static final int FONT_SIZE_CONDITIONS = 4;
    private static final ZebraFont DEFAULT_FONT = ZebraFont.ZEBRA_ZERO;
    private static final int SPACE_LINE = 24;
    private static final int WIDTH_PAGE = 609;
    private static final int HEIGHT_PAGE = 5000; //62,8 cm

    public static String generateLayawayLabel() {
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        return generateHeaderSaleTemplate(headerInformation);
    }

    private static String generateHeaderSaleTemplate(final HeaderInformation headerInformation) {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Title
        zebraElements.add(new ZebraText(225, currentPositionLine, "Apartados", DEFAULT_FONT_SIZE));

        //Section of Information of the store
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.storeName}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.storeAddress}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.storePhoneNumber}", DEFAULT_FONT_SIZE));

        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "No. Transacción:", DEFAULT_FONT_SIZE));
        zebraElements.add(new ZebraText(230, currentPositionLine, "${receipt.nroTransaccion}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "Id. Empleado:", DEFAULT_FONT_SIZE));
        zebraElements.add(new ZebraText(230, currentPositionLine, "${receipt.idEmployee}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "Fecha:", DEFAULT_FONT_SIZE));
        zebraElements.add(new ZebraText(230, currentPositionLine, LocalDateTime.now().toString(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "Referencia #:", DEFAULT_FONT_SIZE));
        zebraElements.add(new ZebraText(230, currentPositionLine, "${receipt.invoiceNumber}", DEFAULT_FONT_SIZE));

        //Section of Conditions
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "La exhibición y comercialización de los artículos objeto de la presente operación, es realizada", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "por EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "con Interese y Garantía Prendaria y al artículo 9 fracción IV de la ley del impuesto al Valor", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "Agregado, por lo que al ser productos usados, no se otorga garantía alguna conforme lo", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "dispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el Cliente ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "verificar su condición y funcionamiento antes de salir del establecimiento, ya que ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "las ventas de los productos son definitivas y no están sujetas a cambio o devolución.", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;

        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "Este apartado esta sujeto a una comisión de $[layaway fee](IVA incluido) no reembolsable. El", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "enganche y/o pago parcial realizado no es reembolsable, Este apartado será cancelado si no se", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "realiza un pago por [layaway next payment amount] en la fecha de pago programada [next payment date].", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "y/o las indicadas en este documento. En cuyo caso el articulo podrá ser", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "comercializado sin responsabilidad para el proveedor. Usted podrá realizar pagos adicionales ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "sin penalización, en los cuales el saldo pendiente será calculado para los pagos programado ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "siguientes: ", FONT_SIZE_CONDITIONS));

        //Section of Information of customer
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.customerName}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.customerAddress}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.customerPhone}", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT, currentPositionLine, "${receipt.customerRFC}", DEFAULT_FONT_SIZE));

        return ZplUtils.generateZplCode(WIDTH_PAGE, SPACE_LINE, DEFAULT_FONT, zebraElements, currentPositionLine);
    }
}
