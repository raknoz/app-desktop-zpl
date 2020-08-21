package demo_zpl.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptPaymentApprovalDto implements Serializable {

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

}
