package com.epam.esm.validation;

import com.epam.esm.EntitiesForValidationsTest;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.IdValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdValidationTest {



    @Test
    public void validateId_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        IdValidation.validateId(EntitiesForValidationsTest.INCORRECT_ID, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateId_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        IdValidation.validateId(EntitiesForValidationsTest.CORRECT_ID, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

}
