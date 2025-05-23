package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DateFromInFutureValidation implements TravelRequestValidation {

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentDateTime))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }
}
