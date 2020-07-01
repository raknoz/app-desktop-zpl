/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.service;

import demo_zpl.model.BodyInformation;
import demo_zpl.model.HeaderInformation;
import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.constant.ZebraRotation;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import fr.w3blog.zpl.model.element.ZebraText;

import java.time.LocalDateTime;

/**
 * @author davidgomez
 */
public class BuildLabel {

    private static final int HEADER_ROW_INIT = 306;
    private static final int MARGIN_LEFT_INIT = 25;
    private static final int DEFAULT_FONT_SIZE = 5;
    private static final ZebraFont DEFAULT_FONT = ZebraFont.ZEBRA_ZERO;
    private static final int SPACE_LINE = 24;
    private static final int BODY_ROW_LINE = 1009;
    private static final int FOOTER_ROW_LINE = 1035;
    private static final int WIDTH_PAGE = 609;
    private static final int HEIGHT_PAGE = 1009;

    public String generateCustomLabel() {

        //Width and Height
        final HeaderInformation headerInformation = MockFactory.getHeaderInformation();
        final BodyInformation bodyInformation = MockFactory.getBodyInformation();
        final ZebraLabel zebraLabel = new ZebraLabel(WIDTH_PAGE, HEIGHT_PAGE);
        zebraLabel.setDefaultZebraFont(DEFAULT_FONT);
        zebraLabel.setDefaultFontSize(DEFAULT_FONT_SIZE);

        zebraLabel.addElement(new ZebraNativeZpl(
                "^FO32,0^GFA,12288,12288,00064,:Z64:\n" +
                        "eJztWU1vG8cZntnligTliCJiVqmRmKwLFAIDqEbQAILqWiuUjC5tQwFcKIcodIv24oOjABakg5Ud00hjOIce8g" +
                        "PKpkBBLAtaR4Eq3HUTw0BPEirVyYHl2kgQgQZWDEBVqsvl9JklKYmURSnuIYfwlXa5Mzsvn3nm/ZgPEtKTnvSkJz" +
                        "3pSU+eRdTm1ZNvtajfdAe+zUIH/y91v5F9VlVVVWOaptdU9elvD1ccahg1ICfFixyqCWozxZk3T6hOD6H7De" +
                        "Nr4NPdjgptmu/M7+z8+4j2ykQ7oOJ0NHDBpSPxw4/NtnIg09GfopBQsXiEfupBu/5Q5Wn4/iPxf5xrL59hHQ2" +
                        "4zufendZ31P2qA49U52pb81Sn/ZuDH+383qZERtrLY50NwL60Hgymnq5OphbbivSQnwA9Iz6PwD9W9Hm+EYsn" +
                        "422Vnq4qbW9d9v7MkY0lCVe0raqtNDNTnDo9tE7xGAqFCMV/UKV4QjFINYJ/ouAiSjA4SELIE1RLhE6jXQs/I" +
                        "2X9TDz251Yy8kpGWmHyysoyWVlZkUUFI7kc+oe6LFkpiIellwv9BdbiX9WupZJx5IFpzisyr0zy1UnOa0TnfJ" +
                        "JbXm7iqUqIF28Jr4tm1fn667zpB1HgG1GX/8BW3ZK41Vc3Zc6fkDucoyRvZchtXEJ/E/qE9G1VfljvrzfxF4tTi5/O" +
                        "TAXxGAbC87wyxFe93OGEcy6LDjFoIuqG8JZyDhfk1fF6mLcG02BGtuF9/YZhyUZGzplyIWewgmGI0grzPzGWQd" +
                        "tAklyBsVaMpWjBv8d/cXK+llqPx1x8a4hXwtwEIjdxeXkV/FN4irn4qPfgAn5gH19y8fF1A3Y+I9tWIG/25c" +
                        "sGs/O2XN6Uc2zENm4z4rXLlmzniWTnly4aA/mm/k9Wh4prMxua6eKbYYG/Ciy+BtZeXvOWrsMQfLWFTwL4H" +
                        "K/t888C35+VgH8ml2P9uewZgyEf5Rj4yrll4I8ZGA3wz2Vko8AwSktjBX8rYcxXJ+evzgNZJSlHU1N8V+er01" +
                        "wD/nYS+JP8HuBhetRdgl+gBa/qtVRdbeJnBH83CM+C01l7c6xs9tkv2xl7k/jLT6QtVrfrtoXRMTJ+22YjeV65" +
                        "WB6xW/zXgs7ajFME/5QjjCvwh0rUWXMqgquXK9yBVYhgnHI4G+dkVq+F6y3+GdLCP4NJ8Iyx/LLB/AYF/4zwCH/h" +
                        "eo4/MpaIP+fOFRgNFhlbjrb4hz+PaXFMApb4dg2u7ujcnAboP8A/IEJAuB8CIMw1Vd/hpl7zePTa+AH8RhQQMpI/x0" +
                        "aM5ct5c+AxvZ0G30A578//FsNnVMgAEC8/splAju6OtOz/0hoVkhL8uXNdL30G/uFSqPQhxhn49/kQ3+Yuf359cY0z" +
                        "HTOYvjv+r6Z+M/XcyohOFFg0t2wI/v25G7B3v5H7wEhjzHJL8I4VNvZRjuUwSmLEmvqpajIGmb5EVHCf0B84jr4qPP" +
                        "4+8F/neolPC5tXhN3v8Qe6ia6oMFK1Ff8NfAkfl8s2uVIuFAzz7OO6fcPOWwP5rbLt5cRbXoJncGY/2hJuQfKbI09" +
                        "a/JNBISHMa8CnDfyww0vIIQgF3QF/1QtQ4F+HUwp8wr8ar7b4N1c+wB/LLRPjVmE5x84YBcT+iuU34BmB/xL6JAPPyF0" +
                        "30kbGcPmPLTf1vY6gH4tfFfg1CtpVXUR8DWb/PFxP8foQj3kRf6h7j8f1z5EZPfwrfQ/f2MO/Us4S+2NjE/a/XX4i" +
                        "l8uZgfJIPR+wiVQX/n/7Rj5dtgT/8uZIax5/qUgp+NME5loH+A/4f8T4OzW55Ky6+GGuKk380l39Id9t54/QbuJHlw" +
                        "g1boAh84uZIMfw8gw3AihzJqJDyt0yMgV0GPdMi7++LhwgPqtSvQqg+7zi2h/TgKm6+CmRD4DvIB1MC/4xVfCPNRxA" +
                        "LL8+wldiVK9UiJy/YWfyZhQcEfhkpOzn5YBdvoPYHdklfWXbsAw7b9rWiN1MAEqxVNS0RHAW/KtE4T+FhVdTziARE" +
                        "0+49hKvhR2nhFVIqoYU6PAK5w/uAp83E6AP+CLUhVULWSmHfA8/XxYdK2SiyzL4YyK4g5XQsqgqZAxEiRiFQhN/foff" +
                        "29mG/bHWQcKd5J8gxqukkedrQ7waFvkP+FVhAngH5xP8IeetBAD8NHwK+Hnjz323pTwrm1cwuv583r5oeLceBfJ2H" +
                        "o2vbJKobeezF8t22nhYNlr8HxRL7xWL9A1CRcKH2zO+KrwrwEt8HF7YwId3NvHHO/Czt1r4bsKXDJZj8ASsBoy/Fp" +
                        "YpXwL/3B0xUZAo4mO531iRjD/srZjjcyk+Ob8Yr6lxOP00+Mca+K8L2JqCJCDmH2SHqqo72CikRE78Aq/UpuNL" +
                        "Al/KEmpv1QbqUp3Y5tsVsRqweX2X8gr41wcIebdC3t4lV2qSvYU1wZNyMwHQ1WAx5GyEPhP8a0MO5ZSv6RV3NuTc" +
                        "1S9xHhbe4f6LuJT5XbFAaQgjIqpFGshmsz4xEAYbxvj38807u1n62ApggXDWTVQ3GRnOklvoLMu2dkxKLO5M7qxP" +
                        "L2KVpSVDmpL0aBNaUmSDjdRiUtmpeu/p62ex6koqqNaSnuntOGYM0aQhElYAJHpoAY44HbhGqGV5TUIudr7dExqkWP" +
                        "5PzXRuAKhw/3FC71aE/oUj9YGflcQc3FmNNBH4PaFmRbYIGd1v/YGUPufz+ZobMZXGJufAiG+7W729SyULRPWGTe" +
                        "hTJPxrjcZ45Z2GaAlNu9TaCMCxxBawE38BbX/HZHNX9GR/1yX5/bej0awvYzV5Ds44oeDMIf4EkN77pmwu0Bqh+7" +
                        "ue5+OhB5q2pq3Ntmr8Lv4h/qAs37BktkDqRLb2qmWfPxe9dfMma+Fj8R/Tdubb9hlUV118MfjXiE7C5t6roZ+HUnP" +
                        "33knO7XVJLPA7NqABLCdgMpCX2SgZt8bZPv9T/rHhv126+f0Wvhak2P4VNw7qYyZq6F/zsgsiEvZfhbRBLRifCGqJ" +
                        "ZsUwaWzCDqjLIjvA5al1UYa+zNs2vb62YZrQ56+m5nUOxnsS5nEOfQ+1wl7zXbjiHlfVPTBwTwFaRwOXsfzswA9bZ" +
                        "JwNwAJWwMvOdmx4OoQuFtdfKjozpYP4+DfD3hqtBN5n4S7KQqKH8ZFtAmZgvEYs+Qb60V3md6qpHX2njT9Mbwaw5q" +
                        "t4vyQd+Ie2hlj6deIHYAIrgDm3Qr8Uha4yU9yY2SiWNg7gexk83osEt0B3jtV/Cj700XUmYhD7BdZdX4fwueTBkx5k" +
                        "HOqmHeH73iP0Wgru6Y8ROfhKQMPzRQwenfhaMsP5dqK9SkAvNPCPs76L3xn7stAXt1FynPUJ0bRYZ5ULLfQvHMn+AP" +
                        "7hKpFuFsTtePZPFQrVa+J29tn1R0+or6puQJP9cx1VnRAZHzk3ERZGVtv/1KO/a08EPqmQyvGj//T+VwT/yqGzrhPL" +
                        "rsDfHe886zuxLHiE6++nvbao73441NAXXdDrxzc8Uh9DoD8TfR8j7ty3S9xJx/eUFl31Q0mYGOuWbTEBqmojLzRviZ" +
                        "h4SHT9qSAibhf2Ztz014B2JTjo6ntX3RINHcQaPOXeu+pHBMpFX+CmW5LaDgZ9At/HuvbiR1SLKW8pelJRSYx4lHgs" +
                        "riiJeFyF3RPJpBJTEslu+hHy0a2+i+f4n06dR/hL2J//6o/+m9FIhvyFkNlb6VOvWOlu+oN0QxtKjTv/fG6WpoCpTM" +
                        "09eO5TLbRKHDI4uxF689WHG930RxmTSPivm2n0RJz9RiLX01jsAd8gPkm66WMZqZv+3EQ8nkzxe68lr07oap" +
                        "JcnYt//LPTv569pHAyNTW5/mbii0m1K/80GQ7X/95HMvQxFifnIwz4lyQLGwPfDeMmYT/Id1EnU1RRRsPbn3yHrlI" +
                        "ORz4fVOQXBy8FLcUhwQ9nXkiak9Pd9BmRfKMB5ifDjGxCPxJhsLngv0t8aTyxrsNPVEU5dUH75BeeZJIukgtKUntP" +
                        "Of2iOmcpVaJ5Y28kJyYPzRdt+BLwfcwvRVr4aamFT8QTO9cV3wT+6NTdgVBCTIqjwcTa+/T8d+ldFaXTimdwYsLTN" +
                        "QBgfd9whPl9ruOP4gOe8EIjCvwIjUh3+mTjtVBs8Tf3kzPaKiaGq4m5eF/86jvqfZH535pMJn45/VrX32os4I9+7+N" +
                        "zL5wzUYoOn2N9BP1JZ1A6I0Uilz6QusafFhsc1GjydGOBG1JD9LTn1QmiCf6DNJFQNSXRTV/EvZ8NYxBcym6wv8LE8" +
                        "QhEnJJmjvmpwCNmPI+nmeY94mO2UX+CxN+QToDISRWbYnaUuxI+gT77Wtoq2f8JSD1wbzyppCc96UlPetKTnv" +
                        "Tkm5T/AWQHiGU=:AB45"
                        /* End image */
                        + "^FT225,228^A0N,28,28^FB118,1,0^FH^FDApartados^FS"));

        //Information of store and the transaction
        // Position X - Y - TEXT - Font
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (0 * SPACE_LINE), headerInformation.getStoreName()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (1 * SPACE_LINE), headerInformation.getStoreAddress()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (2 * SPACE_LINE), headerInformation.getStorePhoneNumber()));

        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (4 * SPACE_LINE), "No. Transacci\\A2n:"));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (5 * SPACE_LINE), "Id. Empleado:"));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (6 * SPACE_LINE), "Fecha:"));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, HEADER_ROW_INIT + (7 * SPACE_LINE), "Referencia #:"));

        zebraLabel.addElement(new ZebraText(230, HEADER_ROW_INIT + (4 * SPACE_LINE), headerInformation.getTrxNumber()));
        zebraLabel.addElement(new ZebraText(230, HEADER_ROW_INIT + (5 * SPACE_LINE), headerInformation.getEmployeeId()));
        zebraLabel.addElement(new ZebraText(230, HEADER_ROW_INIT + (6 * SPACE_LINE), LocalDateTime.now().toString()));
        zebraLabel.addElement(new ZebraText(230, HEADER_ROW_INIT + (7 * SPACE_LINE), headerInformation.getRefNumber()));

        //Conditions
        zebraLabel.addElement(new ZebraNativeZpl(
                "^FT25,528^A0N,17,14^FB553,1,0^FH^FDLa exhibici\\A2n y comercializaci\\A2n de los art\\A1culos objeto de la presente operaci\\A2n, es realizada^FS"
                        + "^FT25,549^A0N,17,14^FB553,1,0^FH^FDpor EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo con ^FS"
                        + "^FT25,570^A0N,17,14^FB553,1,0^FH^FDInterese y Garant\\A1a Prendaria y al art\\A1culo 9 fracci\\A2n IV de la ley del impuesto al Valor^FS"
                        + "^FT25,591^A0N,17,14^FB553,1,0^FH^FDAgregado, por lo que al ser productos usados, no se otorga garant\\A1a alguna conforme lo ^FS"
                        + "^FT25,612^A0N,17,14^FB553,1,0^FH^FDdispuesto por la NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el Cliente verificar su^FS"
                        + "^FT25,633^A0N,17,14^FB553,1,0^FH^FDcondici\\A2n y funcionamiento antes de salir del establecimiento, ya que las ventas de los^FS"
                        + "^FT25,654^A0N,17,14^FB553,1,0^FH^FDproductos son definitivas y no est\\A0n sujetas a cambio o devoluci\\A2n.^FS"));

        //Information of customer
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (0 * SPACE_LINE), headerInformation.getCustomerName()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (1 * SPACE_LINE), headerInformation.getCustomerAddress()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (2 * SPACE_LINE), headerInformation.getCustomerPhoneNumber()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (3 * SPACE_LINE), headerInformation.getCustomerRfc()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (5 * SPACE_LINE), "Cantidad:"));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (6 * SPACE_LINE), "Precio:"));

        zebraLabel.addElement(new ZebraText(170, 817 + (5 * SPACE_LINE), headerInformation.getItemQty()));
        zebraLabel.addElement(new ZebraText(170, 817 + (6 * SPACE_LINE), headerInformation.getItemPrice()));
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 817 + (8 * SPACE_LINE), "Fecha de pago:"));

        //Add Body payment
        int paymentLine = 1;
        for (BodyInformation.Payment payment : bodyInformation.getPayments()) {
            zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, BODY_ROW_LINE + (paymentLine * SPACE_LINE), payment.getDate()));
            paymentLine++;
            zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1009 + (paymentLine * SPACE_LINE), payment.getAmount()));
            paymentLine *= 2;
        }

        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, BODY_ROW_LINE + (paymentLine * SPACE_LINE), "Total pago:"));
        //Sum element of bodyInformation.getPayments()
        zebraLabel.addElement(new ZebraText(170, BODY_ROW_LINE + (paymentLine * SPACE_LINE), "250.00"));
        paymentLine += 1;

        //Add Body pending payment
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Pr\\A2ximo Pago Programado"));
        paymentLine += 1;
        for (BodyInformation.PendingPayment pendingPayment : bodyInformation.getPendingPayments()) {
            zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), pendingPayment.getDate()));
            paymentLine++;
            zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), pendingPayment.getAmount()));
            paymentLine += 2;
        }

        //Add footer
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Fecha vto.final"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "xxxxxxxxxxxxxxxxxxxx"));
        paymentLine+=2;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Saldo Pendiente"));
        paymentLine += 1;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "xxxxxxxxxxxxxxxxxxxx"));
        paymentLine += 2;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Pago Corriente"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "xxxxxxxxxxxxxxxxxxxx"));
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


        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Para cualquier aclaraci\\A2n, duda o sugerencia podr\\A0 realizarla en nuestro servicio de atenci\\A2n al "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "cliente 01 800 364 78 37 en horario de 9:00 am a 6:00 pm "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "o en la Sucursal en donde efectu\\A2 su compra. "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Nuestro horario es de Lunes a Viernes [store open hour] a [store close hour], Sabado [store open hour] a [store close hour],"));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "En este acto autorizo a EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. y a "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "sus empresas filiales, afiliadas controladas o controladoras, al uso de mi "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "informaci\\A2n proporcionada para fines mercadot\\82cnicos y publicitarios, Les "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "instruyo para enviarme por el medio que ustedes estimen conveniente, "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "informaci\\A2n sobre sus aperturas, promociones y publicidad de los bienes y "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "servicios que ustedes proporcionan, sujetando el uso de esta informaci\\A2n a lo "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "establecido en la policita  de privacidad. "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "http//www.empenofacil.com/avisodeprivacidad.html. "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "Cualquier pago que se realice con tarjeta de cr\\82dito o d\\82bito NO causa "));
        paymentLine++;
        zebraLabel.addElement(new ZebraText(MARGIN_LEFT_INIT, 1035 + (paymentLine * SPACE_LINE), "comisi\\A2n.  "));

        return zebraLabel.getZplCode();
    }


}
