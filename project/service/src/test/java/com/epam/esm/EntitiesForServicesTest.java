package com.epam.esm;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.entities.Tag;
import com.epam.esm.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

public class EntitiesForServicesTest {
    // TODO: 17.06.2022 I should check test 
    //GC
    public static final GiftCertificate GIFT_CERTIFICATE_1 = new GiftCertificate(1, "giftCertificate1",
            "description1", new BigDecimal("10.10"), 1, LocalDateTime.parse("2020-08-29T06:12:15"),
            LocalDateTime.parse("2020-08-29T06:12:15"), Arrays.asList(new Tag(2, "tagName3"), new Tag(5, "tagName2")));
    public static final GiftCertificate GIFT_CERTIFICATE_2 = new GiftCertificate(2, "giftCertificate3",
            "description3", new BigDecimal("30.30"), 3, LocalDateTime.parse("2019-08-29T06:12:15"),
            LocalDateTime.parse("2019-08-29T06:12:15"), Collections.singletonList(new Tag(2, "tagName3")));
    public static final GiftCertificate GIFT_CERTIFICATE_3 = new GiftCertificate(3, "giftCertificate2",
            "description2", new BigDecimal("20.20"), 2, LocalDateTime.parse("2018-08-29T06:12:15"),
            LocalDateTime.parse("2018-08-29T06:12:15"), Collections.singletonList(new Tag(3, "tagName5")));

    //User
    public static final User USER_1 = new User(1, "name1");
    public static final User USER_2 = new User(2, "name2");

    //Order
    public static final Order ORDER_1 = new Order(1, new BigDecimal("10.10"),
            LocalDateTime.parse("2018-08-29T06:12:15"), USER_1, GIFT_CERTIFICATE_1);
    public static final Order ORDER_2 = new Order(2, new BigDecimal("30.30"),
            LocalDateTime.parse("2018-08-29T06:12:15"), USER_1, GIFT_CERTIFICATE_2);

    //Tag
    public static final Tag TAG_1 = new Tag(1, "tagName1");
    public static final Tag TAG_2 = new Tag(2, "tagName3");
    public static final Tag TAG_3 = new Tag(3, "tagName5");
    public static final Tag TAG_4 = new Tag(4, "tagName4");
    public static final Tag TAG_5 = new Tag(5, "tagName2");




}
