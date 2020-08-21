package demo_zpl.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ReceiptRetailDto implements Serializable {
    String transactionNumber;
    BigDecimal amountWithTax;
    BigDecimal amountBeforeTax;
    Long cashDrawer;
    String invoiceNumber;
    List<ReceiptItemDto> items;
    Set<ReceiptTenderDto> tenders;
    String transactionDate;
    String transactionTime;
    String transactionType;
}
