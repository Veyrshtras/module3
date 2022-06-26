package com.epam.esm;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.entities.Tag;
import com.epam.esm.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class EntitiesForValidationsTest {

    //GCValidation
    public static final List<Tag> INCORRECT_TAGS = Arrays.asList(new Tag("1"), new Tag("2"));
    public static final List<Tag> CORRECT_TAGS = Arrays.asList(new Tag("tagName1"), new Tag("tagName2"));

    public static final GiftCertificate
            INCORRECT_GIFT_CERTIFICATE = new GiftCertificate(1, " ",
            " ", new BigDecimal("10.115"), 1, LocalDateTime.parse("2020-08-29T06:12:15.156"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), INCORRECT_TAGS);
    public static final GiftCertificate CORRECT_GIFT_CERTIFICATE = new GiftCertificate(0, "giftCertificate",
            "description", new BigDecimal("10.15"), 1, LocalDateTime.parse("2020-08-29T06:12:15.156"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), CORRECT_TAGS);


    //IdValidation

    public static final long INCORRECT_ID = -15;
    public static final long CORRECT_ID = 15;

    //OrderValidation
    public static final Order INCORRECT_ORDER = new Order(1, new BigDecimal("10.5"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), new User(-1, null),
            new GiftCertificate(-5, null, null, new BigDecimal("12"), 2,
                    LocalDateTime.parse("2018-08-29T06:12:15"), LocalDateTime.parse("2018-08-29T06:12:15"), null));

    public static final Order CORRECT_ORDER = new Order(0, new BigDecimal("10.5"),
            LocalDateTime.parse("2020-08-29T06:12:15.156"), new User(1, "name1"),
            new GiftCertificate(5, null, null, new BigDecimal("12"), 2,
                    LocalDateTime.parse("2018-08-29T06:12:15"), LocalDateTime.parse("2018-08-29T06:12:15"), null));

    //TagValidation
    public static final String INCORRECT_NAME = "qw";
    public static final String CORRECT_NAME = "tagName";
    public static final Tag INCORRECT_TAG = new Tag(INCORRECT_NAME);
    public static final Tag CORRECT_TAG = new Tag(CORRECT_NAME);

}
