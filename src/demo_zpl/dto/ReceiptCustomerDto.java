package demo_zpl.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class ReceiptCustomerDto implements Serializable {
    String firstName;
    String lastName;
    String maternalLastName;
    String middleName;
    String phone;
    String email;
    AddressDetailsDto address;
    List<CustomerIdentificationDto> identifications;
}
