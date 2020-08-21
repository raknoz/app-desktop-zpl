package demo_zpl.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ReceiptLayawayDto implements Serializable {
    private BigDecimal restockingFee;
    private BigDecimal layawayFeeBeforeTax;
    private BigDecimal layawayFeeWithTax;
    private List<ReceiptLayawayPaymentDto> paymentHistory;
    private BigDecimal totalPayments;
    private BigDecimal balanceDue;
    private List<ReceiptLayawayPaymentDto> paymentSchedule;
    private String layawayText;
}
