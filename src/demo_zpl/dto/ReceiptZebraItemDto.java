package demo_zpl.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ReceiptZebraItemDto {
    String itemEzid;
    String bulkCategory;
    Boolean typeBulk;
    String qty;
    String categoryName;
    String description;
    BigDecimal amount;
    String heightLabelDescription;
    Boolean pppSelected;
    String pppDescription;
    BigDecimal pppAmount;
    String pppExpDate;
}
