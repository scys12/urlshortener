package com.samuel.urlshortener.data.persistent.mongodb.entities;

import com.samuel.urlshortener.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("email_verifications")
public class EmailVerificationData {
    private static final int EXPIRATION = 60 * 24;

    @Id
    private String id;

    private String token;

    @DBRef
    private UserData user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static EmailVerificationData newInstance(String token, UserData user){
        return new EmailVerificationData(null, token, user, new Date());
    }

}
