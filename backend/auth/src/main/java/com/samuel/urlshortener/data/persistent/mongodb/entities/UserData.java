package com.samuel.urlshortener.data.persistent.mongodb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.samuel.urlshortener.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("users")
public class UserData {
    @Id
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 20)
    @Indexed(unique = true)
    private String username;

    private Date createdDate;

    @Size(max = 20)
    @JsonIgnore
    private String password;

    @Email @Size(max = 50)
    @NotBlank
    @Indexed(unique = true)
    private String email;

    private boolean enabled;

    public static UserData newInstance(User user){
        return new UserData(null, user.getName(), user.getUsername(), new Date(), user.getPassword(), user.getEmail(), false);
    }

    public static UserData newExistingInstance(User user){
        return new UserData(user.getUserId(), user.getName(), user.getUsername(), user.getCreatedDate(), user.getPassword(), user.getEmail(), user.isEnabled());
    }

    public User toUser() {
        return new User(
                userId, name, username, createdDate, null, email, enabled
        );
    }

    public User toExistingUser(){
        return new User(
                userId, name, username, createdDate, password, email, enabled
        );
    }
}
