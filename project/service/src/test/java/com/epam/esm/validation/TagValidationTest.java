package com.epam.esm.validation;

import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.validations.TagValidation;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagValidationTest {

    private static final String INCORRECT_NAME = "qw";
    private static final String CORRECT_NAME = "tagName";
    private static final Tag INCORRECT_TAG = new Tag(INCORRECT_NAME);
    private static final Tag CORRECT_TAG = new Tag(CORRECT_NAME);

    @Test
    public void testValidate_incorrectData() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validate(INCORRECT_TAG, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void testValidate_correctData() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validate(CORRECT_TAG, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void testValidateName_incorrectData() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validateName(INCORRECT_NAME, exceptionResult);
        assertFalse(exceptionResult.getExceptionMessages().isEmpty());
    }

    @Test
    public void testValidateName_correctData() {
        ExceptionResult exceptionResult = new ExceptionResult();
        TagValidation.validateName(CORRECT_NAME, exceptionResult);
        assertTrue(exceptionResult.getExceptionMessages().isEmpty());
    }
}
