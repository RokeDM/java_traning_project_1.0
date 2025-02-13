package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    void testPersonFirstName() {
        var request = requestAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    void testPersonLastName() {
        var request = requestAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    void testAgreementDateFrom() {
        var request = requestAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    void testAgreementDateTo() {
        var request = requestAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    void testAgreementPrice() {
        var request = requestAllFields();
        var response = service.calculatePremium(request);
        assertNotNull(response.getAgreementPrice());
    }

    private TravelCalculatePremiumRequest requestAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Alex");
        request.setPersonLastName("Smith");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        return request;
    }
}