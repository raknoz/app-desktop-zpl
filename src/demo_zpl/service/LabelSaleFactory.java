/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import demo_zpl.model.BodySaleInformation;
import demo_zpl.model.HeaderInformation;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.model.element.ZebraText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davidgomez
 */
public class LabelSaleFactory {

    private int marginTop;
    private int marginLeft;
    private int marginLeftTotal = 100;
    private int fontSize;
    private int fontSizeConditions;
    private int spaceLine;

    private static final ZebraFont DEFAULT_FONT = ZebraFont.ZEBRA_ZERO;
    private static final int WIDTH_PAGE = 804; // 10,1 cm APROX
    private static final int HEIGHT_PAGE = 5000; //62,8 cm APROX

    public String generateSaleLabel() {

        //Setup
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodySaleInformation bodyInformation = MockFactory.getBodySaleInformation();

        final List<String> templates = new ArrayList<>();

        templates.add(generateHeaderSaleTemplate(headerInformation));
        templates.add(generateBodySaleTemplate(bodyInformation));
        templates.add(generateFooterSaleTemplate());

        return String.join("\n", templates);
    }

    private String generateHeaderSaleTemplate(final HeaderInformation headerInformation) {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Title
        zebraElements.add(new ZebraText(225, currentPositionLine, "Venta", fontSize));

        //Section of Information of the store
        currentPositionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getStoreName(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getStoreAddress(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getStorePhoneNumber(), fontSize));

        //Section of Information of customer
        currentPositionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getCustomerName(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getCustomerAddress(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getCustomerPhoneNumber(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, headerInformation.getCustomerRfc(), fontSize));

        //Section of Information of transaction
        currentPositionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "No. Transacci\\A2n:", fontSize));
        zebraElements.add(new ZebraText(230, currentPositionLine, headerInformation.getTrxNumber(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Id. Empleado:", fontSize));
        zebraElements.add(new ZebraText(230, currentPositionLine, headerInformation.getEmployeeId(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Fecha:", fontSize));
        zebraElements.add(new ZebraText(230, currentPositionLine, LocalDateTime.now().toString(), fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Referencia #:", fontSize));
        zebraElements.add(new ZebraText(230, currentPositionLine, headerInformation.getRefNumber(), fontSize));

        //Section of Conditions
        currentPositionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "La exhibición y comercialización de los artículos objeto de la presente operación, es realizada", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "por EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "con Interese y Garantía Prendaria y al artículo 9 fracción IV de la ley del impuesto al Valor", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Agregado, por lo que al ser productos usados, no se otorga garantía alguna conforme lo", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "dispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el Cliente ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "verificar su condición y funcionamiento antes de salir del establecimiento, ya que ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "las ventas de los productos son definitivas y no están sujetas a cambio o devolución.", fontSizeConditions));

        return ZplUtils.generateZplCode(WIDTH_PAGE, spaceLine, DEFAULT_FONT, zebraElements, currentPositionLine);
    }

    private String generateBodySaleTemplate(final BodySaleInformation bodyInformation) {

        //Setup
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Section dynamic of items
        for (final BodySaleInformation.Item item : bodyInformation.getItems()) {
            zebraElements.add(new ZebraText(marginLeft, currentPositionLine, item.getEzId(), fontSize));
            currentPositionLine += spaceLine;
            zebraElements.add(new ZebraText(marginLeft, currentPositionLine, item.getFullDescription(), fontSize));
            currentPositionLine += spaceLine;
            zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Cantidad: " + item.getQuantity(), fontSize));
            currentPositionLine += spaceLine;
            zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Precio: " + item.getUnitPrice().toString(), fontSize));
            currentPositionLine += spaceLine * 2;
        }

        return ZplUtils.generateZplCode(WIDTH_PAGE, spaceLine, DEFAULT_FONT, zebraElements, currentPositionLine);
    }

    private String generateFooterSaleTemplate() {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Section of totals
        zebraElements.add(new ZebraText(marginLeftTotal, currentPositionLine, "Subtotal:", fontSize));
        zebraElements.add(new ZebraText(190, currentPositionLine, "XXXXXXXXXXX", fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeftTotal, currentPositionLine, "IVA:", fontSize));
        zebraElements.add(new ZebraText(190, currentPositionLine, "XXXXXXXXXXX", fontSize));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeftTotal, currentPositionLine, "Total:", fontSize));
        zebraElements.add(new ZebraText(190, currentPositionLine, "XXXXXXXXXXX", fontSize));

        //Section of conditions II
        currentPositionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Para cualquier aclaración, duda o sugerencia podrá realizarla en nuestro servicio de atención al ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "cliente 01 800 364 78 37 en horario de 9:00 am a 6:00 pm o en la Sucursal en donde efectuó su ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "compra. ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Nuestro horario es de Lunes a Viernes " + "XX:XX AM" + " a " + "XX:XX PM"
                + ",Sabado " + "XX:XX AM" + " a " + "XX:XX PM", fontSizeConditions));
        currentPositionLine += spaceLine;

        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "En este acto autorizo a EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. y a ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "sus empresas filiales, afiliadas controladas o controladoras, al uso de mi ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "información proporcionada para fines mercadotécnicos y publicitarios, Les ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "instruyo para enviarme por el medio que ustedes estimen conveniente, ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "información sobre sus aperturas, promociones y publicidad de los bienes y ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "servicios que ustedes proporcionan, sujetando el uso de esta información a lo ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "establecido en la policita  de privacidad. ", fontSizeConditions));
        currentPositionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "http//www.empenofacil.com/avisodeprivacidad.html. ", fontSizeConditions));
        currentPositionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, currentPositionLine, "Cualquier pago que se realice con tarjeta de crédito o débito NO causa comisión.", fontSizeConditions));

        return ZplUtils.generateZplCode(WIDTH_PAGE, spaceLine, DEFAULT_FONT, zebraElements, currentPositionLine);
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginLeftTotal() {
        return marginLeftTotal;
    }

    public void setMarginLeftTotal(int marginLeftTotal) {
        this.marginLeftTotal = marginLeftTotal;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSizeConditions() {
        return fontSizeConditions;
    }

    public void setFontSizeConditions(int fontSizeConditions) {
        this.fontSizeConditions = fontSizeConditions;
    }

    public int getSpaceLine() {
        return spaceLine;
    }

    public void setSpaceLine(int spaceLine) {
        this.spaceLine = spaceLine;
    }
    
    

}
