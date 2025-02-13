package org.javaguru.travel.insurance.core;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

public class TravelCalculatePremiumServiceImplAIEachFieldTest {

    @Test
    void testCalculatePremium_personFirstName() {
        String firstName = "John";
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(firstName);
        request.setPersonLastName("Doe");
        request.setAgreementDateFrom(new Date(125, 0, 1)); // 2025-01-01
        request.setAgreementDateTo(new Date(125, 11, 31)); // 2025-12-31

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNotNull(response);
        assertEquals(firstName, response.getPersonFirstName(), "First name should match");
    }

    @Test
    void testCalculatePremium_personLastName() {
        // Подготовка данных
        String lastName = "Doe";
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName(lastName);
        request.setAgreementDateFrom(new Date(125, 0, 1)); // 2025-01-01
        request.setAgreementDateTo(new Date(125, 11, 31)); // 2025-12-31

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNotNull(response);
        assertEquals(lastName, response.getPersonLastName(), "Last name should match");
    }

    @Test
    void testCalculatePremium_agreementDateFrom() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse("2025-01-01");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(new Date(125, 11, 31)); // 2025-12-31

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNotNull(response);
        assertEquals(dateFrom, response.getAgreementDateFrom(), "Agreement Date From should match");
    }

    @Test
    void testCalculatePremium_agreementDateTo() throws Exception {
        // Преобразование строки в объект Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTo = sdf.parse("2025-12-31");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");
        request.setAgreementDateFrom(new Date(125, 0, 1)); // 2025-01-01
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNotNull(response);
        assertEquals(dateTo, response.getAgreementDateTo(), "Agreement Date To should match");
    }
}
