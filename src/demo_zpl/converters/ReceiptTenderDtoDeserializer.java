package demo_zpl.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo_zpl.dto.ReceiptTenderDto;
import demo_zpl.dto.ReceiptTenderDtoImpl;
import demo_zpl.dto.ReceiptTenderDtoLegacy;
import java.io.IOException;

public class ReceiptTenderDtoDeserializer extends JsonDeserializer<ReceiptTenderDto> {
  @Override
  public ReceiptTenderDto deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException{
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = parser.getCodec().readTree(parser);

    if (node.has("paymentApproval")) {
      return mapper.readValue(node.toString(), ReceiptTenderDtoImpl.class);
    }
    return mapper.readValue(node.toString(), ReceiptTenderDtoLegacy.class);
  }
}

