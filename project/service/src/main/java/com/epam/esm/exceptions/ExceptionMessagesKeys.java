package com.epam.esm.exceptions;

import com.epam.esm.entities.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessagesKeys {

    /**
     * Keys for exception messages associated with {@link com.epam.esm.entities.BaseEntity}.
     */
    public static final String BAD_ID = "identifiable.badID";
    public static final String ID_EXISTENCE = "identifiable.hasId";
    public static final String NO_ENTITY = "identifiable.noObject";


    /**
     * Keys for exception messages associated with {@link com.epam.esm.entities.Tag}.
     */
    public static final String BAD_TAG_NAME = "tag.badName";
    public static final String TAG_EXIST = "tag.alreadyExist";
    public static final String TAG_NOT_FOUND = "tag.notFound";

    /**
     * Keys for exception messages associated with {@link com.epam.esm.entities.GiftCertificate}.
     */
    public static final String BAD_GIFT_CERTIFICATE_NAME = "certificate.badName";
    public static final String BAD_GIFT_CERTIFICATE_DESCRIPTION = "certificate.badDescription";
    public static final String BAD_GIFT_CERTIFICATE_PRICE = "certificate.badPrice";
    public static final String BAD_GIFT_CERTIFICATE_DURATION = "certificate.badDuration";
    public static final String GIFT_CERTIFICATE_NOT_FOUND = "certificate.notFound";
    public static final String GIFT_CERTIFICATE_EXIST = "certificate.alreadyExist";

    /**
     * Keys for exception messages associated with {@link Order}.
     */
    public static final String BAD_USER_ID = "order.badUserID";
    public static final String BAD_GIFT_CERTIFICATE_ID = "order.badGiftCertificateID";

    /**
     * Keys for exception messages associated with {@link com.epam.esm.entities.User}.
     */
    public static final String USER_NOT_FOUND = "user.notFound";

    /**
     * Keys for exception messages associated with {@link java.awt.print.Pageable}.
     */
    public static final String INVALID_PAGINATION = "pagination.invalid";
}
