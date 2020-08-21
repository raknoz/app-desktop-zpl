package demo_zpl.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptLocationDto implements Serializable {
    private Long objectid;
    private String ezid;
    private String locationType;
    private String locationName;
    private String streetName;
    private String streetNumber;
    private String phone;
    private String receiptLocationName;
    private String zip;
    private String city;
    private String stateAlternateText;
    private String stateAlternateTextMX;
    private List<StoreHoursDto> storeHours;
}
