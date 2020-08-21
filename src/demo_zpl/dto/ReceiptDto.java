package demo_zpl.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptDto implements Serializable {

  private static final Map<String, String> STORE_ABBR_MAPPING = Map.of(
      "monday through friday", "M-F",
      "saturday", "Sat",
      "sunday", "Sun",
      "lunes a viernes", "Lu-Vi",
      "sabado", "Sa",
      "sábado", "Sa",
      "domingo", "Do");

  private ReceiptEmployeeDto employee;
  private ReceiptLocationDto location;
  private ReceiptRetailDto retail;
  private ReceiptCustomerDto customer;
  private ReceiptLayawayDto layaway;
  private String gratefulness;
  private String returnPolicyTitle;
  private String returnPolicyBody;

  public String retrieveStoreHours() {

    final List<StoreHoursDto> storeHrs = Optional.ofNullable(location)
        .map(ReceiptLocationDto::getStoreHours).orElse(Collections.emptyList());

    String storeHoursResult = storeHrs.stream()
        .map(stHr -> new StringBuilder()
            .append(STORE_ABBR_MAPPING.get(
                StringUtils.defaultIfBlank(stHr.getDayOfWeek().toLowerCase(), StringUtils.EMPTY)))
            .append((" "))
            .append(shortTime(stHr.getOpenTime()))
            .append(("-"))
            .append(shortTime((stHr.getCloseTime())))
            .append((". ")).toString())
        .collect(Collectors.joining());

    return "Store Hours:" + storeHoursResult;

  }

  /**
   * Validates if a variable has certain length, to avoid errors like outOfRangeException when do a
   * substring
   */
  String shortTime(String origTime) {
    String result = StringUtils.defaultIfBlank(origTime, StringUtils.EMPTY);
    if (result.length() > 2) {
      return result.substring(0, 2);
    } else {
      return result;
    }
  }
}

