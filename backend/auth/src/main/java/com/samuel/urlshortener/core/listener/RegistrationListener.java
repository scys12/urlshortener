package com.samuel.urlshortener.core.listener;

import com.samuel.urlshortener.core.event.OnRegistrationCompleteEvent;
import com.samuel.urlshortener.core.usecase.rabbitmq.RabbitMQSender;
import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import com.samuel.urlshortener.data.persistent.mongodb.repositories.contracts.EmailVerificationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        switch (event.getType()) {
            case "register" :
                this.confirmRegistration(event);
                break;
        }
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserData user = event.getUser();
        String token = UUID.randomUUID().toString();
        emailVerificationRepository.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/email/verify?token=" + token;
        rabbitMQSender.send(EmailObject.newEmailObj(recipientAddress, subject, confirmationUrl, recipientAddress));
    }
    @Data
    @AllArgsConstructor
    public static class EmailObject {
        private String email;
        private String subject;
        private String confirmationUrl;
        private String message;

        public static EmailObject newEmailObj(String email, String subject, String confirmationUrl, String message) {
            return new EmailObject(email, subject, confirmationUrl, message);
        }
    }
}