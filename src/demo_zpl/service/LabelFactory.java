/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import demo_zpl.model.BodyLayawayInformation;
import demo_zpl.model.BodySaleInformation;
import demo_zpl.model.HeaderInformation;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraElement;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import fr.w3blog.zpl.model.element.ZebraText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davidgomez
 */
public class LabelFactory {

    private static final int LOGO_MARGIN_TOP = 70;
    private static final int HEADER_MARGIN_TOP = 130;
    private static final int DEFAULT_MARGIN_LEFT = 25;
    private static final int DEFAULT_MARGIN_LEFT_TOTAL = 100;
    private static final int DEFAULT_FONT_SIZE = 5;
    private static final int FONT_SIZE_CONDITIONS = 4;
    private static final ZebraFont DEFAULT_FONT = ZebraFont.ZEBRA_ZERO;
    private static final int SPACE_LINE = 24;
    private static final int CONDITIONS_STARTER_LINE = 370;
    private static final int BODY_STARTER_LINE = 1009;
    private static final int WIDTH_PAGE = 609;
    private static final int HEIGHT_PAGE = 5000; //62,8 cm

    public static String generateLayawayLabel() {

        //Setup
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodyLayawayInformation bodyLayawayInformation = MockFactory.getBodyInformation();
        final ZebraLabel zebraLabel = new ZebraLabel(WIDTH_PAGE, HEIGHT_PAGE);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setDefaultFontSize(DEFAULT_FONT_SIZE);

        //Title
        zebraLabel.addElement(new ZebraNativeZpl("^FT225,70^A0N,28,28^FB118,1,0^FH^FDApartados^FS"));

        //Information of store and the transaction
        // Position X - Y - TEXT - Font
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (0 * SPACE_LINE), headerInformation.getStoreName()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (1 * SPACE_LINE), headerInformation.getStoreAddress()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (2 * SPACE_LINE), headerInformation.getStorePhoneNumber()));

        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (4 * SPACE_LINE), "No. Transacci\\A2n:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (5 * SPACE_LINE), "Id. Empleado:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (6 * SPACE_LINE), "Fecha:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, HEADER_MARGIN_TOP + (7 * SPACE_LINE), "Referencia #:"));

        zebraLabel.addElement(new ZebraText(230, HEADER_MARGIN_TOP + (4 * SPACE_LINE), headerInformation.getTrxNumber()));
        zebraLabel.addElement(new ZebraText(230, HEADER_MARGIN_TOP + (5 * SPACE_LINE), headerInformation.getEmployeeId()));
        zebraLabel.addElement(new ZebraText(230, HEADER_MARGIN_TOP + (6 * SPACE_LINE), LocalDateTime.now().toString()));
        zebraLabel.addElement(new ZebraText(230, HEADER_MARGIN_TOP + (7 * SPACE_LINE), headerInformation.getRefNumber()));

        //Conditions
        int conditionsHeaderLine = 1;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "La exhibici\\A2n y comercializaci\\A2n de los art\\A1culos objeto de la presente operaci\\A2n, es realizada", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "por EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "con Interese y Garant\\A1a Prendaria y al art\\A1culo 9 fracci\\A2n IV de la ley del impuesto al Valor", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "Agregado, por lo que al ser productos usados, no se otorga garant\\A1a alguna conforme lo", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "dispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el Cliente ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "verificar su condici\\A2n y funcionamiento antes de salir del establecimiento, ya que ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "las ventas de los productos son definitivas y no est\\A0n sujetas a cambio o devoluci\\A2n.", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "Este apartado esta sujeto a una comisión de $XXXXXXXX(IVA incluido) no ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "reembolsable. El enganche y/o pago parcial realizado no es reembolsable, ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "Este apartado ser\\A0 cancelado si no se realiza un pago por $XXXXXXX ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), " en la fecha de pago programada {12/12/2020}. y/ ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "o las indicadas en este documento. En cuyo caso el art\\A1culo podrá ser ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "comercializado sin responsabilidad para el proveedor. Usted podr\\A0 realizar ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "pagos adicionales sin penalización, en los cuales el saldo pendiente ser\\A0 ", FONT_SIZE_CONDITIONS));
        conditionsHeaderLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, CONDITIONS_STARTER_LINE + (conditionsHeaderLine * SPACE_LINE), "calculado para los pagos programados siguientes: ", FONT_SIZE_CONDITIONS));

        //Information of customer
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (0 * SPACE_LINE), headerInformation.getCustomerName()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (1 * SPACE_LINE), headerInformation.getCustomerAddress()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (2 * SPACE_LINE), headerInformation.getCustomerPhoneNumber()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (3 * SPACE_LINE), headerInformation.getCustomerRfc()));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (5 * SPACE_LINE), "Cantidad:"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (6 * SPACE_LINE), "Precio:"));

        zebraLabel.addElement(new ZebraText(170, 817 + (5 * SPACE_LINE), "1"));
        zebraLabel.addElement(new ZebraText(170, 817 + (6 * SPACE_LINE), "2"));
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 817 + (8 * SPACE_LINE), "Fecha de pago:"));

        //Add Body payment
        int paymentLine = 1;
        for (BodyLayawayInformation.Payment payment : bodyLayawayInformation.getPayments()) {
            zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, BODY_STARTER_LINE + (paymentLine * SPACE_LINE), payment.getDate()));
            paymentLine++;
            zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1009 + (paymentLine * SPACE_LINE), payment.getAmount()));
            paymentLine *= 2;
        }

        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, BODY_STARTER_LINE + (paymentLine * SPACE_LINE), "Total pago:"));
        //Sum element of bodyInformation.getPayments()
        zebraLabel.addElement(new ZebraText(170, BODY_STARTER_LINE + (paymentLine * SPACE_LINE), "250.00"));
        paymentLine += 1;

        //Add Body pending payment
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Pr\\A2ximo Pago Programado"));
        paymentLine += 1;
        for (BodyLayawayInformation.PendingPayment pendingPayment : bodyLayawayInformation.getPendingPayments()) {
            zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), pendingPayment.getDate()));
            paymentLine++;
            zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), pendingPayment.getAmount()));
            paymentLine += 2;
        }

        //Add footer
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Fecha vto.final"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "xxxxxxxxxxxxxxxxxxxx"));
        paymentLine += 2;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Saldo Pendiente"));
        paymentLine += 1;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "xxxxxxxxxxxxxxxxxxxx"));
        paymentLine += 2;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Pago Corriente"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "xxxxxxxxxxxxxxxxxxxx"));
        paymentLine += 2;

        zebraLabel.addElement(new ZebraText(194, 1035 + (paymentLine * SPACE_LINE), "Subtotal:"));
        zebraLabel.addElement(new ZebraText(300, 1035 + (paymentLine * SPACE_LINE), "XXXXXXXXXXX"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(50, 1035 + (paymentLine * SPACE_LINE), "Comisi\\A2n de Apartado:"));
        zebraLabel.addElement(new ZebraText(300, 1035 + (paymentLine * SPACE_LINE), "XXXXXXXXXXX"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(254, 1035 + (paymentLine * SPACE_LINE), "IVA:"));
        zebraLabel.addElement(new ZebraText(300, 1035 + (paymentLine * SPACE_LINE), "XXXXXXXXXXX"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(230, 1035 + (paymentLine * SPACE_LINE), "Total:"));
        zebraLabel.addElement(new ZebraText(300, 1035 + (paymentLine * SPACE_LINE), "XXXXXXXXXXX"));
        paymentLine += 3;


        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Para cualquier aclaraci\\A2n, duda o sugerencia podr\\A0 realizarla en nuestro servicio de  ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "atenci\\A2n al cliente 01 800 364 78 37 en horario de 9:00 am a 6:00 pm ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "o en la Sucursal en donde efectu\\A2 su compra. ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Nuestro horario es de Lunes a Viernes 9:00 am a 6:00 pm , Sábado 9:00 am a 6:00 pm,", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "En este acto autorizo a EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. y a ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "sus empresas filiales, afiliadas controladas o controladoras, al uso de mi ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "informaci\\A2n proporcionada para fines mercadot\\82cnicos y publicitarios, Les ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "instruyo para enviarme por el medio que ustedes estimen conveniente, ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "informaci\\A2n sobre sus aperturas, promociones y publicidad de los bienes y ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "servicios que ustedes proporcionan, sujetando el uso de esta informaci\\A2n a lo ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "establecido en la policita  de privacidad. ", FONT_SIZE_CONDITIONS));
        paymentLine += 2;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "http//www.empenofacil.com/avisodeprivacidad.html. ", FONT_SIZE_CONDITIONS));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(DEFAULT_MARGIN_LEFT, 1035 + (paymentLine * SPACE_LINE), "Cualquier pago que se realice con tarjeta de cr\\82dito o d\\82bito NO causa comisi\\A2n.", FONT_SIZE_CONDITIONS));

        return zebraLabel.getZplCode();
    }

    public static String generateSaleLabel() {

        //Setup
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodySaleInformation bodyInformation = MockFactory.getBodySaleInformation();

        final List<String> templates = new ArrayList<>();

        templates.add(generateHeaderSaleTemplate(headerInformation));
        templates.add(generateBodySaleTemplate(bodyInformation));
        templates.add(generateFooterSaleTemplate());

        return String.join("\n", templates);
    }

    private static String generateHeaderSaleTemplate(final HeaderInformation headerInformation) {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Logo
        zebraElements.add(buildZebraTextElement(225, LOGO_MARGIN_TOP, "LOGO EMPRESA", DEFAULT_FONT_SIZE));

        //Title
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(225, currentPositionLine, "Venta", DEFAULT_FONT_SIZE));

        //Section of Information of the store
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getStoreName(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getStoreAddress(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getStorePhoneNumber(), DEFAULT_FONT_SIZE));

        //Section of Information of customer
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getCustomerName(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getCustomerAddress(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getCustomerPhoneNumber(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, headerInformation.getCustomerRfc(), DEFAULT_FONT_SIZE));

        //Section of Information of transaction
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "No. Transacci\\A2n:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(230, currentPositionLine, headerInformation.getTrxNumber(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Id. Empleado:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(230, currentPositionLine, headerInformation.getEmployeeId(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Fecha:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(230, currentPositionLine, LocalDateTime.now().toString(), DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Referencia #:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(230, currentPositionLine, headerInformation.getRefNumber(), DEFAULT_FONT_SIZE));

        //Section of Conditions
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "La exhibición y comercialización de los artículos objeto de la presente operación, es realizada", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "por EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "con Interese y Garantía Prendaria y al artículo 9 fracción IV de la ley del impuesto al Valor", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Agregado, por lo que al ser productos usados, no se otorga garantía alguna conforme lo", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "dispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el Cliente ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "verificar su condición y funcionamiento antes de salir del establecimiento, ya que ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "las ventas de los productos son definitivas y no están sujetas a cambio o devolución.", FONT_SIZE_CONDITIONS));

        final ZebraLabel zebraLabel = new ZebraLabel(WIDTH_PAGE, currentPositionLine + SPACE_LINE * 4);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setZebraElements(zebraElements);
        return zebraLabel.getZplCode();
    }

    private static String generateBodySaleTemplate(final BodySaleInformation bodyInformation) {

        //Setup
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Section dynamic of items

        currentPositionLine += SPACE_LINE * 2;
        for (final BodySaleInformation.Item item : bodyInformation.getItems()) {
            zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, item.getEzId(), DEFAULT_FONT_SIZE));
            currentPositionLine += SPACE_LINE;
            zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, item.getFullDescription(), DEFAULT_FONT_SIZE));
            currentPositionLine += SPACE_LINE;
            zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Cantidad: " + item.getQuantity(), DEFAULT_FONT_SIZE));
            currentPositionLine += SPACE_LINE;
            zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Precio: " + item.getUnitPrice().toString(), DEFAULT_FONT_SIZE));
            currentPositionLine += SPACE_LINE * 2;
        }

        final ZebraLabel zebraLabel = new ZebraLabel(WIDTH_PAGE, currentPositionLine + SPACE_LINE * 4);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setZebraElements(zebraElements);
        return zebraLabel.getZplCode();
    }

    private static String generateFooterSaleTemplate() {
        final List<ZebraElement> zebraElements = new ArrayList<>();
        int currentPositionLine = 50;

        //Section of totals
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT_TOTAL, currentPositionLine, "Subtotal:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(190, currentPositionLine, "XXXXXXXXXXX", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT_TOTAL, currentPositionLine, "IVA:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(190, currentPositionLine, "XXXXXXXXXXX", DEFAULT_FONT_SIZE));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(new ZebraText(DEFAULT_MARGIN_LEFT_TOTAL, currentPositionLine, "Total:", DEFAULT_FONT_SIZE));
        zebraElements.add(buildZebraTextElement(190, currentPositionLine, "XXXXXXXXXXX", DEFAULT_FONT_SIZE));

        //Section of conditions II
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Para cualquier aclaraci\\A2n, duda o sugerencia podrá realizarla en nuestro servicio de atenci\\A2n al ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "cliente 01 800 364 78 37 en horario de 9:00 am a 6:00 pm o en la Sucursal en donde efectu\\A2 su ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "compra. ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Nuestro horario es de Lunes a Viernes" + "#####################" + " a " + "#########################" +
                ",Sabado " + "##################" + " a " + "##############", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;

        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "En este acto autorizo a EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. y a ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "sus empresas filiales, afiliadas controladas o controladoras, al uso de mi ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "información proporcionada para fines mercadotécnicos y publicitarios, Les ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "instruyo para enviarme por el medio que ustedes estimen conveniente, ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "información sobre sus aperturas, promociones y publicidad de los bienes y ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "servicios que ustedes proporcionan, sujetando el uso de esta información a lo ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "establecido en la policita  de privacidad. ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE * 2;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "http//www.empenofacil.com/avisodeprivacidad.html. ", FONT_SIZE_CONDITIONS));
        currentPositionLine += SPACE_LINE;
        zebraElements.add(buildZebraTextElement(DEFAULT_MARGIN_LEFT, currentPositionLine, "Cualquier pago que se realice con tarjeta de crédito o débito NO causa comisión.", FONT_SIZE_CONDITIONS));

        final ZebraLabel zebraLabel = new ZebraLabel(WIDTH_PAGE, currentPositionLine + SPACE_LINE * 4);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setZebraElements(zebraElements);
        return zebraLabel.getZplCode();
    }

    private static ZebraElement buildZebraTextElement(final int marginLeft, final int currentPositionLine, final String text, final int fontSize) {
        String tmpText = "";
        if (text != null && text.length() > 0) {
            tmpText = text.replaceAll("ó", "\\\\\\\\A2")
                    .replaceAll("é", "\\\\\\\\82")
                    .replaceAll("í", "\\\\\\\\A1")
                    .replaceAll("á", "\\\\\\\\A0")
                    .replaceAll("ú", "\\\\\\\\BA")
                    .replaceAll("ü", "\\\\\\\\BC")
                    .replaceAll("Ó", "\\\\\\\\93")
                    .replaceAll("É", "\\\\\\\\89")
                    .replaceAll("Í", "\\\\\\\\8D")
                    .replaceAll("Á", "\\\\\\\\81")
                    .replaceAll("Ú", "\\\\\\\\9A")
                    .replaceAll("Ü", "\\\\\\\\9C");
        }
        return new ZebraText(marginLeft, currentPositionLine, tmpText, fontSize);
    }
}
