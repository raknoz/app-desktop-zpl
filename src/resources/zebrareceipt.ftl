<#-- Template for receipt -->
<#setting number_format=",##0.00">
<#setting date_format="dd/MM/yyyy">
<#setting locale="en_US">

<#assign location = receipt.location>
<#assign retail = receipt.retail>
<#assign employee = receipt.employee>
<#assign customer = receipt.customer>
<#assign tenderins = receipt.tendersIn>
<#assign isLayaway = receipt.isLayaway?then(true, false)>
<#if isLayaway>
    <#assign layaway = receipt.layaway>
</#if>
<#assign space_lines = 27>

<#-- ####################################################### -->
<#-- ################### Header's section ################## -->
<#-- #############B######################################### -->

<#-- ############## Logo's section ##################### -->
${receipt.logo}
<#-- ##############  ##################### -->

<#-- ############## Store's section ##################### -->
^XA
^POI
^LH0,0
^PW804
^LL895
^CI27
^FO45,50^A0N,21,20^FB700,1,5,C,^FH\^FD ${location.locationName}^FS
^FO45,77^A0N,21,20^FB700,1,5,C,^FH\^FD${location.streetName} ${(location.streetNumber)!"(Sin número)"}, ${(location.stateAlternateTextMX)! location.stateAlternateText} (${location.zip})^FS
^FO45,104^A0N,21,20^FB700,1,5,C,^FH\^FD${(location.phone)!"- -"}^FS
^FO45,173^A0N,21,20^FB700,20,6,C,^FH\^FD${receipt.gratefulness}^FS
^FO45,290^A0N,21,20^FB700,1,5,C,^FH\^FD ${receipt.isLayaway?then("APARTADO", "VENTA")} ^FS

<#-- ############## Customer's section ##################### -->
^FT45,360^A0N,21,20^FH\^FD${(receipt.customerName)!"- -"}^FS
^FT45,387^A0N,21,20^FH\^FD${(receipt.customerAddress)!"- -"}^FS
^FT45,414^A0N,21,20^FH\^FDTeléfono: ${(customer.phone)!"- -"}^FS
^FT45,441^A0N,21,20^FH\^FDRFC #: ${(receipt.customerRfc)!"- -"}^FS

<#-- ############## Transaction's section ##################### -->
^FT45,495^A0N,21,20^FH\^FDNo. Transacción:^FS
^FT230,495^A0N,21,20^FH\^FD${(retail.transactionNumber)!"- -"}^FS
^FT45,522^A0N,21,20^FH\^FDId. Empleado:^FS
^FT230,522^A0N,21,20^FH\^FD${(employee.employeeEzid)!"- -"}^FS
^FT45,549^A0N,21,20^FH\^FDFecha:^FS
^FT230,549^A0N,21,20^FH\^FD${(receipt.transactionDateTime)!"- -"}^FS
^FT45,576^A0N,21,20^FH\^FDReferencia #:^FS
^FT230,576^A0N,21,20^FH\^FD${(retail.invoiceNumber)!"- -"}^FS

<#-- ############## Conditions's section ################### -->
^FO45,630^A0N,21,20^FB700,20,5,0,J^FH\^FD La exhibición y comercialización de los artículos objeto de la presente operación, es realizada por EZPAWN
\b MANAGEMENT MEXICO S. DE R.L. DE C.V. al amparo del contrato de mutuo con Interese y Garantía Prendaria y al artículo 9 fracción IV de la ley del
\b impuesto al Valor agregado, por lo que al ser productos usados, no se otorga garantía alguna conforme lo dispuesto por la
\b NOM-017-SCFI-1993/NOM-024-SCFI-1998, debiendo el  Cliente verificar su condición y funcionamiento antes de salir del establecimiento, ya que las
ventas de los productos son definitivas y no están sujetas a cambio o devolución.^FS

^FT440,880^A0N,21,20^FH\^FDCantidad^FS
^FT640,880^A0N,21,20^FH\^FDPrecio^FS
^XZ

<#-- ####################################################### -->
<#-- ################### Body's section #################### -->
<#-- ####################################################### -->

<#list receipt.items as item>

<#-- ################### item section #################### -->
    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL27
    ^CI27
    ^FT45,27^A0N,21,20^FH\^FD${(item.itemEzid)!(item.bulkCategory)}^FS
    ^FT440,27^A0N,21,20^FH\^FD${item.qty}^FS
    ^FT640,27^A0N,21,20^FH\^FD$ ${item.amount}^FS
    ^XZ

    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL${(item.heightLabelDescription)! "15"}
    ^CI27
<#-- if not present item's description will draw a empty tag -->
    <#if item.heightLabelDescription??>
        <#if item.typeBulk?then(true,false)>
            ^FO45,27^A0N,21,20^FB350,10,8,L^FH\^FD${(item.description)! ""}^FS
        <#else>
            ^FO45,27^A0N,21,20^FH\^FD${(item.categoryName)! ""}^FS
            ^FO45,54^A0N,21,20^FB350,10,8,L^FH\^FD${(item.description)! ""}^FS
        </#if>
    <#else>
        ^FT45,15^A0N,21,20^FH\^FD ^FS
    </#if>
    ^XZ

<#-- ################### PPP item section #################### -->
    <#if item.pppSelected>
        ^XA
        ^POI
        ^LH0,0
        ^PW804
        ^LL70
        ^CI27
        ^FO45,27^A0N,21,20^FB400,1,0,L^FH\^FD${item.pppDescription}^FS
        ^FO440,27^A0N,21,20^FH\^FD1^FS
        ^FO640,27^A0N,21,20^FH\^FD$ ${item.pppAmount}^FS
        ^FO45,54^A0N,21,20^FB700,1,0,L^FH\^FD${(item.itemEzid)!(item.bulkCategory)} (Vencimiento ${item.pppExpDate})^FS
        ^XZ
    </#if>
</#list>


<#-- ###################  total's section #################### -->
<#assign totals_axis_y = 81>  <#-- Init position of label -->
^XA
^POI
^LH0,0
^PW804
^LL${receipt.isLayaway?then("185","150")}
^CI27
^FT45,${totals_axis_y}^A0N,21,20^FH\^FDSubtotal:^FS
^FT640,${totals_axis_y}^A0N,21,20^FH\^FD$ ${retail.amountBeforeTax}^FS
<#if receipt.isLayaway?then(true,false)>
    <#assign totals_axis_y += space_lines>
    ^FT45,${totals_axis_y}^A0N,21,20^FH\^FDComisión de Apartado:^FS
    ^FT640,${totals_axis_y}^A0N,21,20^FH\^FD$ ${layaway.layawayFeeBeforeTax}^FS
</#if>
<#assign totals_axis_y += space_lines>
^FT45,${totals_axis_y}^A0N,21,20^FH\^FDIVA:^FS
^FT640,${totals_axis_y}^A0N,21,20^FH\^FD$ ${receipt.IVA}^FS
<#assign totals_axis_y += space_lines>
^FT45,${totals_axis_y}^A0N,21,20^FH\^FDTotal:^FS
^FT640,${totals_axis_y}^A0N,21,20^FH\^FD$ ${retail.amountWithTax}^FS
^XZ

<#-- ###################  layaway payment section #################### -->
<#if receipt.isLayaway?then(true,false)>

<#-- ###################  list of layaway history payment #################### -->
    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL33
    ^CI27
    ^FT45,27^A0N,21,20^FH\^FDHistorial de pagos^FS
    ^FT250,27^A0N,21,20^FH\^FDSaldo^FS
    ^FT400,27^A0N,21,20^FH\^FDSaldo a pagar hoy^FS
    ^XZ

    <#list layaway.paymentHistory as paymentHistory>
        ^XA
        ^POI
        ^LH0,0
        ^PW804
        ^LL33
        ^CI27
        ^FT45,27^A0N,21,20^FH\^FD${paymentHistory.date?date("MM/dd/yyyy")?date}^FS
        ^FT400,27^A0N,21,20^FH\^FD$ ${paymentHistory.amount}^FS
        ^XZ
    </#list>

<#-- ###################  totals of layaway history payment #################### -->
    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL87
    ^CI27
    ^FT45,54^A0N,21,20^FH\^FDTotal pagos:^FS
    ^FT250,54^A0N,21,20^FH\^FD$ ${layaway.totalPayments}^FS
    ^FT45,81^A0N,21,20^FH\^FDSaldo adeudado:^FS
    ^FT250,81^A0N,21,20^FH\^FD$ ${layaway.balanceDue}^FS
    ^XZ

<#-- ###################  list of layaway next payments #################### -->
    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL60
    ^CI27
    ^FT45,54^A0N,21,20^FH\^FDPróximos Pagos Programados^FS
    ^FT400,54^A0N,21,20^FH\^FDSaldo^FS
    ^XZ

    <#list layaway.paymentSchedule as schedule>
        ^XA
        ^POI
        ^LH0,0
        ^PW804
        ^LL33
        ^CI27
        ^FT45,27^A0N,21,20^FH\^FD${schedule.date?date("MM/dd/yyyy")?date}^FS
        ^FT400,27^A0N,21,20^FH\^FD$ ${schedule.amount}^FS
        ^XZ
    </#list>

<#-- ###################  Considtions of layaway #################### -->
    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL${receipt.heightLabelLayaway}
    ^CI27
    ^FO45,54^A0N,21,20^FB700,15,5,J,^FH\^FD${layaway.layawayText}^FS
    ^XZ
</#if>

<#-- ###################  Summary transaction section #################### -->
^XA
^POI
^LH0,0
^PW804
^LL175
^CI27
^FO45,27^A0N,21,20^FB700,1,0,C,^FH\^FDResumen ${isLayaway?then("del Apartado", "de la Venta")} ^FS
^FT45,108^A0N,21,20^FH\^FDSubtotal:^FS
^FT640,108^A0N,21,20^FH\^FD$ ${retail.amountBeforeTax}^FS
^FT45,135^A0N,21,20^FH\^FDIVA:^FS
^FT640,135^A0N,21,20^FH\^FD$ ${receipt.IVA}^FS
^FT45,162^A0N,21,20^FH\^FDTotal:^FS
^FT640,162^A0N,21,20^FH\^FD$ ${retail.amountWithTax}^FS
^XZ

<#-- ###################  Tender-in section #################### -->
<#list tenderins?keys as tikey>
    ^XA
    ^POI
    ^LH0,0
    ^PW804
    ^LL33
    ^CI27
    ^FT45,27^A0N,21,20^FH\^FDTipo de pago: ${tikey}^FS
    ^FT640,27^A0N,21,20^FH\^FD$ ${tenderins[tikey]}^FS
    ^XZ
</#list>

<#-- ###################  Tender-Out section #################### -->
^XA
^POI
^LH0,0
^PW804
^LL50
^CI27
^FT45,27^A0N,21,20^FH\^FDCambio:^FS
^FT640,27^A0N,21,20^FH\^FD$ ${receipt.charge}^FS
^XZ

<#-- ####################################################### -->
<#-- ############## Footer's section ####################### -->
<#-- ####################################################### -->

<#-- ###################  Return Conditions section #################### -->
^XA
^POI
^LH0,0
^PW804
^LL${receipt.heightLabelReturnPolicy}
^CI27
^FT45,54^A0N,21,20^FB700,1,0,C,^FH\^FD${receipt.returnPolicyTitle}^FS
^FO45,87^A0N,21,20^FB700,29,5,J,^FH\^FD${receipt.returnPolicyBody}^FS
^XZ

<#-- ###################  Conditions footer section #################### -->
^XA
^POI
^LH0,0
^PW804
^LL550
^CI27
^FO45,27^A0N,21,20^FB700,20,5,j,^FH\^FDPara cualquier aclaración, duda o sugerencia podrá realizarla en nuestro servicio de atención al
\b cliente 01 800 364 78 37 en horario de 9:00 am a 6:00 pm o en la Sucursal en donde efectuó su compra. Nuestro horario es de ${receipt.attentionSchedule}.
\b En este acto autorizo a EZPAWN MANAGEMENT MEXICO S. DE R.L. DE C.V. y a sus empresas filiales, afiliadas controladas o
\b controladoras, al uso de mi información proporcionada para fines mercadotécnicos y publicitarios, Les instruyo para enviarme por el medio que
\b ustedes estimen conveniente, información sobre sus aperturas, promociones y publicidad de los bienes y servicios que ustedes proporcionan,
\b sujetando el uso de esta información a lo establecido en la policita de privacidad.^FS

^FT45,350^A0N,21,20^FH\^FDhttp://www.empenofacil.com/avisodeprivacidad.html.^FS

^FT45,390^A0N,21,20^FH\^FDCualquier pago que se realice con tarjeta de crédito o débito NO causa comisión.^FS

^FT45,500^A0N,21,20^FH\^FD(Rev 12/2013)^FS

^XZ