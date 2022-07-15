package com.epam.esm.validation;

import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.IdValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.epam.esm.EntitiesForValidationsTest.CORRECT_ID;
import static com.epam.esm.EntitiesForValidationsTest.INCORRECT_ID;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class IdValidationTest {



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
