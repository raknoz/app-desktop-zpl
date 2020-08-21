package demo_zpl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiptItemPPPDTO implements Serializable {
    private Integer ppp;
    private BigDecimal pppAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDateTime pppExpDate;
    private Boolean pppIsTaxable;

    private String type;
    private String getDescription;
    private BigDecimal guranteedLoanAmount;
    private Integer months;
    private String msgPrompt;
    private String displayText;
    private String shortDisplayText;

    //TODO if Since the PPP/JVIP has a setting for number of months, both versions will have to be converted:
    //     JVIP Months = 1200: Convert to say ‘Lifetime’ (we looked at this yesterday)
    //     JVIP Months = 12: Convert to say ‘1 Year’ (new one attached for reference)
    //     Examples:
    //       If JVIP Months = 18: Convert to say ’18 Months’
    //       If JVIP Months = 24: Convert to say ’24 Months’
    //       So 12 and 1200 are the only 2 special cases.
    private String monthsDescription;
}
