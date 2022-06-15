package com.epam.esm.validation;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.entities.User;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.OrderValidation;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderValidationTest {

    private static final Order INCORRECT_ORDER = new Order(1, new BigDecimal("10.5"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), new User(-1, null),
            new GiftCertificate(-5, null, null, new BigDecimal("12"), 2,
                    LocalDateTime.parse("2018-08-29T06:12:15"), LocalDateTime.parse("2018-08-29T06:12:15"), null));

    private static final Order CORRECT_ORDER = new Order(0, new BigDecimal("10.5"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), new User(1, "name1"),
            new GiftCertificate(5, null, null, new BigDecimal("12"), 2,
                    LocalDateTime.parse("2018-08-29T06:12:15"), LocalDateTime.parse("2018-08-29T06:12:15"), null));

    @Test
    public void validate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        OrderValidation.validate(INCORRECT_ORDER, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        OrderValidation.validate(CORRECT_ORDER, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
