package com.samuel.urlshortener.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private String userId;
    private String name;
    private String username;
    private Date createdDate;
    @JsonIgnore
    private String password;
    private String email;

    public static User newInstance(String name, String username,  String password, String email) {
        return new User(null, name, username, new Date(), password, email);
    }
}
