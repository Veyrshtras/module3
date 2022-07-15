package com.epam.esm.validation;

import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.OrderValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.epam.esm.EntitiesForValidationsTest.CORRECT_ORDER;
import static com.epam.esm.EntitiesForValidationsTest.INCORRECT_ORDER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class OrderValidationTest {



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
