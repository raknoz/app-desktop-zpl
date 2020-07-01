package demo_zpl.service;

import demo_zpl.model.BodyInformation;
import demo_zpl.model.HeaderInformation;

import java.util.Collections;

public class MockFactory {

    public static HeaderInformation getHeaderInformation() {
        return new HeaderInformation("El Store de EZ", "Dirección 1234", "123456", "AAAB 123", "1234566", "00001", "Customer Name", "Customer Address", "012012121212", "ADBVD 1234", "1", "50");
    }

    public static BodyInformation getBodyInformation() {
        final BodyInformation.Payment payment = new BodyInformation.Payment("25/12/2020", "125.00");
        final BodyInformation.PendingPayment pendingPayment = new BodyInformation.PendingPayment("25/01/2021", "125.00");
        return new BodyInformation(Collections.singletonList(payment), Collections.singletonList(pendingPayment));
    }
}
