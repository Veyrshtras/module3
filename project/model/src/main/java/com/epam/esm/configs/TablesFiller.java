package com.epam.esm.configs;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.entities.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TablesFiller {


    public static List<GiftCertificate> giftCertificates=gc();
    public static List<Tag> tags=tg();
    public static List<User> users=us();

    private static List<GiftCertificate> gc(){
        giftCertificates=new ArrayList<>();
        for (int i=1;i<=10000;i++){
            GiftCertificate obj=new GiftCertificate();
            obj.setName("giftCert_"+i);
            obj.setDescription("desc_"+i);
            obj.setPrice(new BigDecimal(Math.random()*5+1));
            obj.setDuration(i%330);
            giftCertificates.add(obj);

        }
        return giftCertificates;
    }
    private static List<Tag> tg(){
        tags=new ArrayList<>();
        for (int i=1;i<=1000;i++){

            Tag tag=new Tag();
            tag.setName("tagName_"+i);
            tags.add(tag);

        }
        return tags;
    }
    private static List<User> us(){

        users=new ArrayList<>();
        for (int i=1;i<=1000;i++){
            User us=new User();
            us.setName("userName_"+i);
            users.add(us);
        }
        return users;
    }
}
