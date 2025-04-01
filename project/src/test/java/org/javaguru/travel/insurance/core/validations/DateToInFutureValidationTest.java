package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.DateTimeService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateToInFutureValidationTest {

    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    DateToInFutureValidation validation;

    @Test
    void shouldReturnErrorWhenDateToIsInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("31.03.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.04.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateToInFuture(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateTo");
        assertEquals(errorOpt.get().getMessage(), "Must be in the future!");
    }

    @Test
    void shouldNotReturnErrorWhenAgreementDateToIsInTheFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.04.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("31.03.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateToInFuture(request);
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