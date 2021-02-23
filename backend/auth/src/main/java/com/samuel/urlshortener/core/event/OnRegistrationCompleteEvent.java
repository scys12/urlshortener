package com.samuel.urlshortener.core.event;

import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private UserData user;
    private final String type;
    public OnRegistrationCompleteEvent(
            UserData user, String appUrl, final String type) {
        super(user);

        this.user = user;
        this.appUrl = appUrl;
        this.type = type;
    }

    // standard getters and setters
}
