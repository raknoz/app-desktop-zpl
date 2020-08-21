package demo_zpl.dto;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class AddressDetailsDto implements Serializable {

  private String addressLine1;
  private String addressLine2;
  private String city;
  private String county;
  private String addressState;
  private String country;
  private String postalCode;
  private String postalCodeSuffix;
  private String typeOfAddress;
  private String homeStatus;
  private String streetName;
  private String streetNo;
  private String aptSuit;
  private Integer stateId;
  private Integer countryId;
}

