package demo_zpl.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import static demo_zpl.enums.TenderType.CASH;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(as = ReceiptTenderDtoImpl.class)
public class ReceiptTenderDtoImpl implements ReceiptTenderDto, Serializable {
    //TODO tenderTYpe CS/CC are 147001/147004:
    //     select * from metalistelement where metadatum_id = 147004 -> "Credit Card" / "Tarjeta de crédito"
    //     select * from metalistelement where metadatum_id = 147001 -> "Cash" /  "Dinero en efectivo"
    //TODO create a JIRA ticket to use metalistelement table in a future to look for the tenderTypeDisplayText & tenderTypeDisplayTextMx in the metalistelement like queries above.
    private String tenderType;
    private BigDecimal tenderAmount;
    private String tenderTypeDisplayText;
    private String tenderTypeDisplayTextMx;
    private String tenderFlow;
    private String clientIpAddress;
    private ReceiptPaymentApprovalDto paymentApproval;

    @Override
    public String getTenderType() {
        return this.tenderType;
    }

    @Override
    public BigDecimal getTenderAmount() {
        return this.tenderAmount;
    }

    @Override
    public String getTenderFlow() {
        return this.tenderFlow;
    }

    @Override
    public String buildTenderTypeDisplay() {
        if (CASH.getType().equals(tenderType)) {
            return tenderTypeDisplayTextMx;
        } else {
            return paymentApproval.getAppLabel() + " " + paymentApproval.getCardNumber();
        }
    }
}
