package org.javaguru.travel.insurance.core;
import static org.junit.jupiter.api.Assertions.*;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class TravelCalculatePremiumServiceImplAIOneTest {

    @Test
    void testCalculatePremium_withRealDate() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        request.setAgreementDateFrom(new Date(125, 0, 1)); // 2025-01-01
        request.setAgreementDateTo(new Date(125, 11, 31)); // 2025-12-31

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNotNull(response);
        assertEquals("John", response.getPersonFirstName());
        assertEquals("Doe", response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}
