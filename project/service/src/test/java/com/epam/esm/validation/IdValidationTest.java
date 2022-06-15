package com.epam.esm.validation;

import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.IdValidation;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdValidationTest {

    private static final String INCORRECT_SORT_TYPE = "sort";
    private static final String CORRECT_SORT_TYPE = "ASC";
    private static final long INCORRECT_ID = -15;
    private static final long CORRECT_ID = 15;

    @Test
    public void validateId_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        IdValidation.validateId(INCORRECT_ID, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateId_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        IdValidation.validateId(CORRECT_ID, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

}
