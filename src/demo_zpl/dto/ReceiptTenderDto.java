package demo_zpl.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import demo_zpl.converters.ReceiptTenderDtoDeserializer;
import java.math.BigDecimal;

@JsonDeserialize(using = ReceiptTenderDtoDeserializer.class)
public interface ReceiptTenderDto {
  String getTenderType();
  String getTenderFlow();
  BigDecimal getTenderAmount();
  String buildTenderTypeDisplay();
}
