package com.epam.esm.validation;


import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.GiftCertificateValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.epam.esm.EntitiesForValidationsTest.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateValidationTest {



    @Test
    public void validate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validate(INCORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validate(CORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateForUpdate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateForUpdate(INCORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateForUpdate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateForUpdate(CORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateListOfTags_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateListOfTags(INCORRECT_TAGS, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateListOfTags_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateListOfTags(CORRECT_TAGS, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
