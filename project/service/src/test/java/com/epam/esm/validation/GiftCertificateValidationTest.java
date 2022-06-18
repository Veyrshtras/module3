package com.epam.esm.validation;


import com.epam.esm.EntitiesForValidationsTest;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.GiftCertificateValidation;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GiftCertificateValidationTest {



    @Test
    public void validate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validate(EntitiesForValidationsTest.INCORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validate(EntitiesForValidationsTest.CORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateForUpdate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateForUpdate(EntitiesForValidationsTest.INCORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateForUpdate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateForUpdate(EntitiesForValidationsTest.CORRECT_GIFT_CERTIFICATE, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateListOfTags_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateListOfTags(EntitiesForValidationsTest.INCORRECT_TAGS, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateListOfTags_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        GiftCertificateValidation.validateListOfTags(EntitiesForValidationsTest.CORRECT_TAGS, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
