package com.epam.esm.validations;

import com.epam.esm.entities.Order;
import com.epam.esm.exceptions.ExceptionResult;
import lombok.experimental.UtilityClass;
import static com.epam.esm.exceptions.ExceptionMessagesKeys.*;

@UtilityClass
public class OrderValidation {

    private final int MIN_ID = 1;

    public void validate(Order order, ExceptionResult er) {
        IdValidation.validateExistenceOfId(order.getId(), er);
        validateUserId(order.getUser().getId(), er);
        validateGiftCertificateId(order.getCertificate().getId(), er);
    }

    public void validateUserId(long userId, ExceptionResult er) {
        if (userId < MIN_ID) {
            er.addException(BAD_USER_ID, userId);
        }
    }


    public void validateGiftCertificateId(long giftCertificateId, ExceptionResult er) {
        if (giftCertificateId < MIN_ID) {
            er.addException(BAD_GIFT_CERTIFICATE_ID, giftCertificateId);
        }
    }
}
