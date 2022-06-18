package com.epam.esm.validation;

import com.epam.esm.EntitiesForValidationsTest;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.TagValidation;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagValidationTest {


    @Test
    public void validate_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validate(EntitiesForValidationsTest.INCORRECT_TAG, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validate_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validate(EntitiesForValidationsTest.CORRECT_TAG, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateName_incorrectDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validateName(EntitiesForValidationsTest.INCORRECT_NAME, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void validateName_correctDataTest() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validateName(EntitiesForValidationsTest.CORRECT_NAME, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
