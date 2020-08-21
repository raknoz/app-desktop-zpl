package demo_zpl.dto;

import demo_zpl.enums.TransactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import org.apache.commons.collections4.CollectionUtils;

/**
 * DTO with the information to match with the template of Sale ticket of Zebra
 * Printer.
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReceiptZebraPrinterDto implements Serializable {

    private static final String FORMAT_DATE_US = "MM/dd/yyyy";
    private static final String FORMAT_DATE_MX = "dd/MM/yyyy";
    private static final String TENDER_OUT = "OUT";
    private static final String TENDER_IN = "IN";
    private static final String CASH = "CS";
    private static final String EMPTY_CONTENT = "- -";
    // General config label
    private static final int MIN_HEIGHT_LABEL_DESC = 48;
    private static final int SPACE_BTW_LINES_DESC = 30;
    private static final int MAX_CHAR_LENGTH_DESC = 37;
    //PPP Config label
    private static final int MIN_HEIGHT_LABEL_PPP = 120;
    private static final int MAX_CHAR_DESC_PPP = 84;
    private static final int SPACE_BTW_LINES_PPP = 30;

    private static final String MIN_QTY_BULK_ITEM = "1";
    private final static Map<String, Integer> orderScheduleStore
            = Map.of("lunes a viernes", 0,
                    "monday through friday", 0,
                    "saturday", 1,
                    "sabado", 1,
                    "sábado", 1,
                    "sunday", 2,
                    "domingo", 2);

    String attentionSchedule;
    String employeeId;
    String transactionDateTime;
    String customerName;
    String customerAddress;
    String customerRfc;
    ReceiptRetailDto retail;
    ReceiptEmployeeDto employee;
    ReceiptLocationDto location;
    ReceiptCustomerDto customer;
    List<ReceiptZebraItemDto> items;
    Map<String, BigDecimal> tendersIn;
    BigDecimal IVA;
    ReceiptLayawayDto layaway;
    Boolean isLayaway;
    String heightLabelLayaway;
    BigDecimal charge;
    String gratefulness;
    String returnPolicyTitle;
    String returnPolicyBody;
    String heightLabelReturnPolicy;

    public ReceiptZebraPrinterDto(final ReceiptDto receiptDto) {
        this.customer = receiptDto.getCustomer();
        this.employee = receiptDto.getEmployee();
        this.location = receiptDto.getLocation();
        this.retail = receiptDto.getRetail();
        this.customerName = buildCustomerFullName(receiptDto.getCustomer());
        this.customerAddress = buildCustomerAddress(receiptDto.getCustomer().getAddress());
        this.customerRfc = getRfcCustomer(receiptDto.getCustomer().getIdentifications());
        this.attentionSchedule = getStoreAttentionSchedule(receiptDto.getLocation().getStoreHours());
        this.transactionDateTime
                = convertToNewDateFormat(receiptDto.getRetail().getTransactionDate(), receiptDto.getRetail().getTransactionTime());
        this.IVA = receiptDto.getRetail().getAmountWithTax().subtract(receiptDto.getRetail().getAmountBeforeTax());
        this.items = processReceiptItem(receiptDto.getRetail().getItems());
        this.employeeId = receiptDto.getEmployee().getEmployeeEzid();
        this.layaway = receiptDto.getLayaway();
        this.isLayaway = TransactionType.isLayaway(receiptDto.getRetail().getTransactionType());
        this.heightLabelLayaway = nonNull(layaway)
                ? heightLabelByText(layaway.getLayawayText(), MAX_CHAR_DESC_PPP, MIN_HEIGHT_LABEL_PPP, SPACE_BTW_LINES_PPP) : null;
        this.tendersIn = getTendersIn(receiptDto.getRetail());
        this.charge = getTenderOutAmount(receiptDto.getRetail());
        this.gratefulness = receiptDto.getGratefulness();
        this.returnPolicyTitle = receiptDto.getReturnPolicyTitle();
        this.returnPolicyBody = receiptDto.getReturnPolicyBody();
        this.heightLabelReturnPolicy = heightLabelByText(receiptDto.getReturnPolicyBody(), MAX_CHAR_DESC_PPP, MIN_HEIGHT_LABEL_PPP,
                SPACE_BTW_LINES_PPP);
    }

    /**
     * Method to build a full name of customer.
     *
     * @param customer Object with customer's information.
     * @return A String with full name.
     */
    private String buildCustomerFullName(final ReceiptCustomerDto customer) {
        return String.format("%s %s %s %s",
                customer.getFirstName(),
                StringUtils.defaultIfBlank(customer.getMiddleName(), ""),
                customer.getLastName(),
                StringUtils.defaultIfBlank(customer.getMaternalLastName(), ""));
    }

    /**
     * Method to build full address of customer including colony and another
     * reference.
     *
     * @param addressCustomer Object with information about the Address of
     * customer.
     * @return A String with full address.
     */
    private String buildCustomerAddress(final AddressDetailsDto addressCustomer) {
        //This validation if for tickets from US
        if (isNull(addressCustomer)) {
            return EMPTY_CONTENT;
        }

        return String.format(
                "%s %s, %s (%s), %s, %s", addressCustomer.getStreetName(), addressCustomer.getStreetNo(),
                addressCustomer.getAddressLine2(), addressCustomer.getPostalCode(), addressCustomer.getCountry(),
                addressCustomer.getCity());
    }

    /**
     * Method to return the customer's RFCs number identification.
     *
     * @param identifications list of identifications of the customer.
     * @return A String with the identification number or empty.
     */
    private String getRfcCustomer(List<CustomerIdentificationDto> identifications) {
        final String IDENTIFICATION_RFC = "RFC";
        //This validation if for tickets from US
        if (isNull(identifications)) {
            return EMPTY_CONTENT;
        }

        return identifications.stream()
                .filter(identification -> IDENTIFICATION_RFC.equalsIgnoreCase(identification.getType()))
                .map(CustomerIdentificationDto::getValue)
                .findFirst()
                .orElse(EMPTY_CONTENT);
    }

    /**
     * Method to convert the transaction date and time in a new format.
     *
     * @param date Date of the transaction MM/dd/YYYY
     * @param time Time of the transaction hh:MM AM/PM
     * @return A String in a new format dd/MM/YYYY hh:MM AM/PM
     */
    private String convertToNewDateFormat(final String date, final String time) {
        return String.format("%s %s", DateTimeFormatter.ofPattern(FORMAT_DATE_MX)
                .format(DateTimeFormatter.ofPattern(FORMAT_DATE_US).parse(date)), time);
    }

    /**
     * Create schedule of the store to publish on the ticket.
     *
     * @param attentionSchedules List with days and schedule hours of the store.
     * @return A String with the information of attention schedules.
     */
    private String getStoreAttentionSchedule(final List<StoreHoursDto> attentionSchedules) {

        if (CollectionUtils.isEmpty(attentionSchedules)) {
            return EMPTY_CONTENT;
        }

        final List<StoreHoursDto> attentionSchedulesCopy = new ArrayList<>(attentionSchedules);
        attentionSchedulesCopy.sort(Comparator.comparing(sh -> orderScheduleStore.get(sh.getDayOfWeek().toLowerCase())));
        final List<String> tmpAttentionSchedules = new ArrayList<>();
        attentionSchedules.forEach(sh -> {
            tmpAttentionSchedules.add(String.format("%s de %s a %s", sh.getDayOfWeek(), sh.getOpenTime().substring(0, 5),
                    sh.getCloseTime().substring(0, 5)));
        });

        return String.join(", ", tmpAttentionSchedules);
    }

    private BigDecimal getTenderOutAmount(final ReceiptRetailDto retailDto) {
        return retailDto.getTenders().stream()
                .filter(
                        tender -> TENDER_OUT.equalsIgnoreCase(tender.getTenderFlow()))
                .map(ReceiptTenderDto::getTenderAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<String, BigDecimal> getTendersIn(final ReceiptRetailDto receiptRetailDto) {
        return Optional.ofNullable(receiptRetailDto.getTenders()).orElse(Collections.emptySet()).stream()
                .filter(tender -> TENDER_IN.equalsIgnoreCase(tender.getTenderFlow()))
                .collect(Collectors.toMap(ReceiptTenderDto::buildTenderTypeDisplay, ReceiptTenderDto::getTenderAmount));
    }

    /**
     * Cast the Receipt Item and add information util for create the label.
     *
     * @param receiptItems items for receipt.
     * @return A list with custom information.
     */
    private List<ReceiptZebraItemDto> processReceiptItem(final List<ReceiptItemDto> receiptItems) {
        final List<ReceiptZebraItemDto> customItems = new ArrayList<>();

        for (final ReceiptItemDto item : receiptItems) {
            final boolean isPppSelected = nonNull(item.getPppSelected());

            customItems.add(ReceiptZebraItemDto.builder()
                    .itemEzid(item.getItemEzid())
                    .typeBulk(isBulkItem(item))
                    .categoryName(item.getCategoryName())
                    .bulkCategory("Bulk " + item.getBulkCategory())
                    .qty(getItemQuantity(item))
                    .amount(item.getSoldPrice())
                    .description(item.getDescription())
                    .heightLabelDescription(
                            heightLabelByText(item.getDescription(), MAX_CHAR_LENGTH_DESC, SPACE_BTW_LINES_DESC, MIN_HEIGHT_LABEL_DESC))
                    .pppSelected(isPppSelected)
                    .pppAmount(isPppSelected ? item.getPppSelected().getPppAmount() : null)
                    .pppDescription(isPppSelected ? item.getPppSelected().getGetDescription() : null)
                    .pppExpDate(isPppSelected
                            ? getPlanExpirationDate(item.getPppSelected()) : null)
                    .build());
        }

        return customItems;
    }

    /**
     * Little function to calculate how many lines has a description to custom
     * the zebra label.
     *
     * @param text text to process.
     * @param charsPerLine The max number of characters per line.
     * @param minHeightLabel Min height of the label.
     * @param spaceBtwLines spaces between lines in dots.
     * @return A Integer about the height of label in dots.
     */
    private String heightLabelByText(final String text, final int charsPerLine, final int minHeightLabel, final int spaceBtwLines) {

        //Value to indicate that no description present.
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        int extraLines = 0;
        int charCount = 0;
        final String[] words = text.split(" ");

        for (final String word : words) {
            if (charCount + word.length() >= charsPerLine) {
                extraLines += 1;
                charCount = 0;
            } else {
                //Add + 1 to simulate space
                charCount += word.length() + 1;
            }
        }
        return String.valueOf(minHeightLabel + (extraLines * spaceBtwLines));
    }

    /**
     * Determinate if an Item is bulk.
     *
     * @param item item.
     * @return boolean.
     */
    private boolean isBulkItem(final ReceiptItemDto item) {
        return ((Optional.ofNullable(item.getBulkQuantity()).orElse(0)) > 0 && (item.getBulkCategory() != null));
    }

    /**
     * Calculate the time of expiration of PPP.
     *
     * @param itemPPP PPP selected.
     * @return a Date in format dd/MM/yyyy with the expires of PPP.
     */
    private String getPlanExpirationDate(final ReceiptItemPPPDTO itemPPP) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_MX);
            final Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(this.transactionDateTime));
            cal.add(Calendar.MONTH, itemPPP.getMonths());
            return sdf.format(cal.getTime());
        } catch (ParseException pex) {
            return "";
        }
    }

    /**
     * get the quantity of item in base on the item type.
     *
     * @param item item of retail.
     * @return an Integer with the quantity.
     */
    private String getItemQuantity(final ReceiptItemDto item) {
        if (isBulkItem(item)) {
            return String.valueOf(item.getBulkQuantity());
        }
        return MIN_QTY_BULK_ITEM;
    }
}
