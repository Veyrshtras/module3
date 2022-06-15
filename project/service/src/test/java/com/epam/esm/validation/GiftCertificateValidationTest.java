package com.epam.esm.validation;


import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.GiftCertificateValidation;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GiftCertificateValidationTest {

    private static final List<Tag> INCORRECT_TAGS = Arrays.asList(new Tag("1"), new Tag("2"));
    private static final List<Tag> CORRECT_TAGS = Arrays.asList(new Tag("tagName1"), new Tag("tagName2"));

    private static final GiftCertificate
            INCORRECT_GIFT_CERTIFICATE = new GiftCertificate(1, " ",
            " ", new BigDecimal("10.115"), 1, LocalDateTime.parse("2020-08-29T06:12:15.156"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), INCORRECT_TAGS);
    private static final GiftCertificate CORRECT_GIFT_CERTIFICATE = new GiftCertificate(0, "giftCertificate",
            "description", new BigDecimal("10.15"), 1, LocalDateTime.parse("2020-08-29T06:12:15.156"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), CORRECT_TAGS);

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
