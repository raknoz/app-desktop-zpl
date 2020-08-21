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
@JsonDeserialize(as = ReceiptTenderDtoLegacy.class)
@Deprecated
/**
 * Used for current APK 3.0 and release 4.1. Needs until all stores are using
 * new request for transactions
 */
public class ReceiptTenderDtoLegacy implements ReceiptTenderDto, Serializable {

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
    private String creditApproval;
    private String paymentServiceData;
    private String settled;
    private Integer creditCardEntryMode;
    private String complianceCode;
    private String appLabel;
    private String pinVerified;
    private String fccAid;
    private String fccTvr;
    private String fccIad;
    private String fccTsi;
    private String fccArc;
    private String cardNumber;
    private Integer cardType;
    private String fccCardEntry;
    private String expirationDate;
    private String flag;

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
            return appLabel + " " + cardNumber;
        }
    }
}
