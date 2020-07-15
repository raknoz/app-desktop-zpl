/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import demo_zpl.model.BodySaleInformation;
import demo_zpl.model.HeaderInformation;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
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
    private int marginLeftTotal = 400;
    private final int marginLeftItemsList = 400;
    private int fontSize;
    private int fontSizeConditions;
    private int spaceLine;

    private static final int WIDTH_PAGE_DOTS = 804; // 10,1 cm APROX

    public String generateSaleLabel() {

        //Setup
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodySaleInformation bodyInformation = MockFactory.getBodySaleInformation();

        final List<String> templates = new ArrayList<>();

        ResultZPLProcess resultZPLProcess = generateHeaderSaleTemplate(marginTop, headerInformation);
        templates.add(ZplCustomUtils.generateZplCode(WIDTH_PAGE_DOTS, spaceLine, resultZPLProcess.getZebraElements(), resultZPLProcess.getLastPosition()));
        resultZPLProcess = generateBodySaleTemplate(marginTop, bodyInformation);
        templates.add(ZplCustomUtils.generateZplCode(WIDTH_PAGE_DOTS, spaceLine, resultZPLProcess.getZebraElements(), resultZPLProcess.getLastPosition()));
        resultZPLProcess = generateFooterSaleTemplate(marginTop);
        templates.add(ZplCustomUtils.generateZplCode(WIDTH_PAGE_DOTS, spaceLine, resultZPLProcess.getZebraElements(),
                resultZPLProcess.getLastPosition()));

        return String.join("\n", templates);
    }

    public String generateSaleLabelAllInOne() {

        //Setup
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodySaleInformation bodyInformation = MockFactory.getBodySaleInformation();

        ResultZPLProcess result = generateHeaderSaleTemplate(marginTop, headerInformation);
        final List<ZebraElement> zebraElements = new ArrayList<>(result.getZebraElements());
        result = generateBodySaleTemplate(result.getLastPosition(), bodyInformation);
        zebraElements.addAll(result.getZebraElements());
        result = generateFooterSaleTemplate(result.getLastPosition());
        zebraElements.addAll(result.getZebraElements());

        return ZplCustomUtils.generateZplCode(WIDTH_PAGE_DOTS, spaceLine, zebraElements, result.getLastPosition());
    }

    private ResultZPLProcess generateHeaderSaleTemplate(final int currentPositionLine, final HeaderInformation headerInformation) {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int positionLine = currentPositionLine;

        //Title
        zebraElements.add(new ZebraText(400, positionLine, "VENTA", fontSize));

        //Section of Information of the store
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getStoreName(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getStoreAddress(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getStorePhoneNumber(), fontSize));

        //Section of Information of customer
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getCustomerName(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getCustomerAddress(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getCustomerPhoneNumber(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, headerInformation.getCustomerRfc(), fontSize));

        //Section of Information of transaction
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "No. Transacción:", fontSize));
        zebraElements.add(new ZebraText(230, positionLine, headerInformation.getTrxNumber(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "Id. Empleado:", fontSize));
        zebraElements.add(new ZebraText(230, positionLine, headerInformation.getEmployeeId(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "Fecha:", fontSize));
        zebraElements.add(new ZebraText(230, positionLine, LocalDateTime.now().toString(), fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "Referencia #:", fontSize));
        zebraElements.add(new ZebraText(230, positionLine, headerInformation.getRefNumber(), fontSize));

        //Section of Conditions
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "La exhibición y comercialización de los artículos objeto de la presente operación, es", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "realizada por EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de ", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "mutuo con Interese y Garantía Prendaria y al artículo 9 fracción IV de la ley del impuesto ", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "al Valor agregado, por lo que al ser productos usados, no se otorga garantía alguna ", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "conforme lo dispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el ", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "Cliente verificar su condición y funcionamiento antes de salir del establecimiento, ya que ", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "las ventas de los productos son definitivas y no están sujetas a cambio o devolución.", fontSizeConditions));
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeftItemsList, positionLine, "Cantidad", fontSize));
        zebraElements.add(new ZebraText(marginLeftItemsList + 200, positionLine, "Precio", fontSize));
        positionLine += spaceLine * 2;

        return new ResultZPLProcess(zebraElements, positionLine);
    }

    private ResultZPLProcess generateBodySaleTemplate(final int currentPositionLine, final BodySaleInformation bodyInformation) {

        //Setup
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int positionLine = currentPositionLine;

        //Section dynamic of items
        for (final BodySaleInformation.Item item : bodyInformation.getItems()) {
            zebraElements.add(new ZebraText(marginLeft, positionLine, item.getEzId(), fontSize));
            zebraElements.add(new ZebraText(marginLeftItemsList, positionLine, String.valueOf(item.getQuantity()), fontSize));
            zebraElements.add(new ZebraText(marginLeftItemsList + 200, positionLine, item.getUnitPrice().toString(), fontSize));
            positionLine += spaceLine;
            final String formZebraBlock = "^FO%d,%d^A0N,%s,%s^FB350,12,,^FH\\^FD%s^FS";
            final Integer[] fontSizeDots = ZplCustomUtils.extractDotsFromFont(fontSize);
            final ZebraNativeZpl descriptionItem = new ZebraNativeZpl(String.format(formZebraBlock, marginLeft, positionLine,
                    fontSizeDots[0],
                    fontSizeDots[1],
                    item.getFullDescription()));
            //zebraElements.add(new ZebraText(marginLeft, positionLine, item.getFullDescription(), fontSize));
            zebraElements.add(descriptionItem);
            /* Hago el cálculo de cuantas líneas ocupa la descripción, estimando que ^FB350,12,,
             * son 25 caracteres por línea SOLO SI LA FUENTE ES 5 SINO HAY QUE BAJAR EL DIVISOR.
             */
            int lines = item.getFullDescription().length() / 25;
            /* ****** */
            positionLine += spaceLine * lines;
            positionLine += spaceLine;
        }

        return new ResultZPLProcess(zebraElements, positionLine);
    }

    private ResultZPLProcess generateFooterSaleTemplate(final int currentPositionLine) {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        //Add more lines to create distance in the end.
        int positionLine = currentPositionLine + (spaceLine * 3);

        //Section of totals
        zebraElements.add(new ZebraText(marginLeftTotal, positionLine, "Subtotal:", fontSize));
        zebraElements.add(new ZebraText(marginLeftItemsList + 190, positionLine, "$00000000000", fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeftTotal, positionLine, "IVA:", fontSize));
        zebraElements.add(new ZebraText(marginLeftItemsList + 190, positionLine, "$00000000000", fontSize));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeftTotal, positionLine, "Total:", fontSize));
        zebraElements.add(new ZebraText(marginLeftItemsList + 190, positionLine, "$00000000000", fontSize));

        //Section of conditions II
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "Para cualquier aclaración, duda o sugerencia podrá realizarla en nuestro servicio de", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "atención al cliente 01 800 364 78 37 en horario de 9:00 am a 6:00 pm o en la Sucursal", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "en donde efectuó su compra. ", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "Nuestro horario es de Lunes a Viernes " + "XX:XX AM" + " a " + "XX:XX PM"
                + ", Sábado " + "XX:XX AM" + " a " + "XX:XX PM", fontSizeConditions));
        positionLine += spaceLine;

        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "En este acto autorizo a EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. y a sus empresas", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "filiales, afiliadas controladas o controladoras, al uso de mi información proporcionada para", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "fines mercadotécnicos y publicitarios, Les instruyo para", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "enviarme por el medio que ustedes estimen conveniente, información sobre sus aperturas,", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                " promociones y publicidad de los bienes y servicios que ustedes proporcionan,", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "sujetando el uso de esta información a lo establecido en la policita de privacidad.", fontSizeConditions));
        //positionLine += spaceLine;
        //zebraElements.add(new ZebraText(marginLeft, positionLine, " ", fontSizeConditions));
        positionLine += spaceLine * 2;
        zebraElements.add(new ZebraText(marginLeft, positionLine, "http//www.empenofacil.com/avisodeprivacidad.html.", fontSizeConditions));
        positionLine += spaceLine;
        zebraElements.add(new ZebraText(marginLeft, positionLine,
                "Cualquier pago que se realice con tarjeta de crédito o débito NO causa comisión.", fontSizeConditions));

        return new ResultZPLProcess(zebraElements, positionLine);
    }

    private class ResultZPLProcess {
        private List<ZebraElement> ZebraElements;
        private int lastPosition;

        public ResultZPLProcess(final List<ZebraElement> ZebraElements, final int lastPosition) {
            this.ZebraElements = ZebraElements;
            this.lastPosition = lastPosition;
        }

        public List<ZebraElement> getZebraElements() {
            return ZebraElements;
        }

        public int getLastPosition() {
            return lastPosition;
        }
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
