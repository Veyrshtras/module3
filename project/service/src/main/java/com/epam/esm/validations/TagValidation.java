package com.epam.esm.validations;

import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.ExceptionResult;
import lombok.experimental.UtilityClass;

import static com.epam.esm.exceptions.ExceptionMessagesKeys.*;

@UtilityClass
public class TagValidation {

    private final int MAX_LENGTH_NAME = 20;
    private final int MIN_LENGTH_NAME = 3;


    public void validate(Tag tag, ExceptionResult er) {
        validateName(tag.getName(), er);
    }


    public void validateName(String name, ExceptionResult er) {
        if (name == null || name.length() < MIN_LENGTH_NAME || name.length() > MAX_LENGTH_NAME) {
            er.addException(BAD_TAG_NAME, name);
        }
    }
}
