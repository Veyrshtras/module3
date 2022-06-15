package com.epam.esm.validations;

import com.epam.esm.exceptions.ExceptionResult;
import lombok.experimental.UtilityClass;
import static com.epam.esm.exceptions.ExceptionMessagesKeys.*;

@UtilityClass
public class IdValidation {

    private final int MIN_ID = 1;
    private final int EMPTY_ID = 0;


    public void validateId(long id, ExceptionResult er) {
        if (id < MIN_ID) {
            er.addException(BAD_ID, id);
        }
    }


    public void validateExistenceOfId(long id, ExceptionResult er) {
        if (id != EMPTY_ID) {
            er.addException(ID_EXISTENCE);
        }
    }
}
