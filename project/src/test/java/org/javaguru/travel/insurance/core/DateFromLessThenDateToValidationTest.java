package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.core.validations.DateFromLessThenDateToValidation;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DateFromLessThenDateToValidationTest {

    private DateFromLessThenDateToValidation validation = new DateFromLessThenDateToValidation();

    @Test
    void shouldReturnErrorWhenAgreementDateToLessThanAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        Optional<ValidationError> errorOp = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOp.isPresent());
        assertEquals(errorOp.get().getField(), "agreementDateFrom");
        assertEquals(errorOp.get().getMessage(), "Must be less than agreementDateTo!");
    }

    @Test
    void shouldReturnErrorWhenAgreementDateToEqualsAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateFrom");
        assertEquals(errorOpt.get().getMessage(),"Must be less than agreementDateTo!");
    }

    @Test
    void shouldNotReturnErrorWhenAgreementDateFromIsLessThanAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}