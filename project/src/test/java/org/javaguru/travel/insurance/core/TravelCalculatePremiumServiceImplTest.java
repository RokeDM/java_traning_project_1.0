package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUP() {
        request = requestAllFields();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
    }

    @Test
    void testPersonFirstName() {
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    void testPersonLastName() {
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    void testAgreementDateFrom() {
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    void testAgreementDateTo() {
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    void testAgreementPrice() {
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