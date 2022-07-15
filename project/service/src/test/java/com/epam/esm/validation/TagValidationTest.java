package com.epam.esm.validation;

import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.TagValidation;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.epam.esm.EntitiesForValidationsTest.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TagValidationTest {


    @Test
    public void validate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validate(INCORRECT_TAG, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validate(CORRECT_TAG, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateName_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validateName(INCORRECT_NAME, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateName_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validateName(CORRECT_NAME, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
