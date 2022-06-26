package com.epam.esm.validation;

import com.epam.esm.EntitiesForValidationsTest;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.OrderValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderValidationTest {



    @Test
    public void validate_incorrectDataTest() {

        ExceptionResult exceptionResult = new ExceptionResult();
        OrderValidation.validate(EntitiesForValidationsTest.INCORRECT_ORDER, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        OrderValidation.validate(EntitiesForValidationsTest.CORRECT_ORDER, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
