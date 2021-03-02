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
        String message =
                "<!doctype html>\n" +
                        "<html>\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <title>Activating Account</title>\n" +
                        "    <style>\n" +
                        "        /* -------------------------------------\n" +
                        "        INLINED WITH htmlemail.io/inline\n" +
                        "    ------------------------------------- */\n" +
                        "        /* -------------------------------------\n" +
                        "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                        "    ------------------------------------- */\n" +
                        "        @media only screen and (max-width: 620px) {\n" +
                        "            table[class=body] h1 {\n" +
                        "                font-size: 28px !important;\n" +
                        "                margin-bottom: 10px !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] p,\n" +
                        "            table[class=body] ul,\n" +
                        "            table[class=body] ol,\n" +
                        "            table[class=body] td,\n" +
                        "            table[class=body] span,\n" +
                        "            table[class=body] a {\n" +
                        "                font-size: 16px !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .wrapper,\n" +
                        "            table[class=body] .article {\n" +
                        "                padding: 10px !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .content {\n" +
                        "                padding: 0 !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .container {\n" +
                        "                padding: 0 !important;\n" +
                        "                width: 100% !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .main {\n" +
                        "                border-left-width: 0 !important;\n" +
                        "                border-radius: 0 !important;\n" +
                        "                border-right-width: 0 !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .btn table {\n" +
                        "                width: 100% !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .btn a {\n" +
                        "                width: 100% !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            table[class=body] .img-responsive {\n" +
                        "                height: auto !important;\n" +
                        "                max-width: 100% !important;\n" +
                        "                width: auto !important;\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        /* -------------------------------------\n" +
                        "        PRESERVE THESE STYLES IN THE HEAD\n" +
                        "    ------------------------------------- */\n" +
                        "        @media all {\n" +
                        "            .ExternalClass {\n" +
                        "                width: 100%;\n" +
                        "            }\n" +
                        "\n" +
                        "            .ExternalClass,\n" +
                        "            .ExternalClass p,\n" +
                        "            .ExternalClass span,\n" +
                        "            .ExternalClass font,\n" +
                        "            .ExternalClass td,\n" +
                        "            .ExternalClass div {\n" +
                        "                line-height: 100%;\n" +
                        "            }\n" +
                        "\n" +
                        "            .apple-link a {\n" +
                        "                color: inherit !important;\n" +
                        "                font-family: inherit !important;\n" +
                        "                font-size: inherit !important;\n" +
                        "                font-weight: inherit !important;\n" +
                        "                line-height: inherit !important;\n" +
                        "                text-decoration: none !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            #MessageViewBody a {\n" +
                        "                color: inherit;\n" +
                        "                text-decoration: none;\n" +
                        "                font-size: inherit;\n" +
                        "                font-family: inherit;\n" +
                        "                font-weight: inherit;\n" +
                        "                line-height: inherit;\n" +
                        "            }\n" +
                        "\n" +
                        "            .btn-primary table td:hover {\n" +
                        "                background-color: #34495e !important;\n" +
                        "            }\n" +
                        "\n" +
                        "            .btn-primary a:hover {\n" +
                        "                background-color: #34495e !important;\n" +
                        "                border-color: #34495e !important;\n" +
                        "            }\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body class=\"\"\n" +
                        "    style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                        "    <span class=\"preheader\"\n" +
                        "        style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\">This\n" +
                        "        is preheader text. Some clients will show this text as a preview.</span>\n" +
                        "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\"\n" +
                        "        style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                        "        <tr>\n" +
                        "            <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                        "            <td class=\"container\"\n" +
                        "                style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                        "                <div class=\"content\"\n" +
                        "                    style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                        "\n" +
                        "                    <!-- START CENTERED WHITE CONTAINER -->\n" +
                        "                    <table class=\"main\"\n" +
                        "                        style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                        "\n" +
                        "                        <!-- START MAIN CONTENT AREA -->\n" +
                        "                        <tr>\n" +
                        "                            <td class=\"wrapper\"\n" +
                        "                                style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                        "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                        "                                    style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                        "                                    <tr>\n" +
                        "                                        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                        "                                            <p\n" +
                        "                                                style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">\n" +
                        "                                                Hi " + user.getName() + ",</p>\n" +
                        "                                            <p\n" +
                        "                                                style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">\n" +
                        "                                                You can activate your account with this button :</p>\n" +
                        "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\"\n" +
                        "                                                style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                        "                                                <tbody>\n" +
                        "                                                    <tr>\n" +
                        "                                                        <td align=\"left\"\n" +
                        "                                                            style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                        "                                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                        "                                                                style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                        "                                                                <tbody>\n" +
                        "                                                                    <tr>\n" +
                        "                                                                        <td\n" +
                        "                                                                            style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                        "                                                                            <a href=\""+ confirmationUrl +"\"\n" +
                        "                                                                                target=\"_blank\"\n" +
                        "                                                                                style=\"display: inline-block; color: #ffffff; background-color: #3498db; border: solid 1px #3498db; border-radius: 5px; box-sizing: border-box; cursor: pointer; text-decoration: none; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-transform: capitalize; border-color: #3498db;\">\n" +
                        "                                                                                Activate Account\n" +
                        "                                                                            </a> </td>\n" +
                        "                                                                    </tr>\n" +
                        "                                                                </tbody>\n" +
                        "                                                            </table>\n" +
                        "                                                        </td>\n" +
                        "                                                    </tr>\n" +
                        "                                                </tbody>\n" +
                        "                                            </table>\n" +
                        "                                            <p\n" +
                        "                                                style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">\n" +
                        "                                                After activate your account, you can use our feature as well.</p>\n" +
                        "                                            <p\n" +
                        "                                                style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">\n" +
                        "                                                Good luck! Hope it works.</p>\n" +
                        "                                        </td>\n" +
                        "                                    </tr>\n" +
                        "                                </table>\n" +
                        "                            </td>\n" +
                        "                        </tr>\n" +
                        "\n" +
                        "                        <!-- END MAIN CONTENT AREA -->\n" +
                        "                    </table>\n" +
                        "\n" +
                        "                    <!-- START FOOTER -->\n" +
                        "                    <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                        "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                        "                            style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                        "                            <tr>\n" +
                        "                                <td class=\"content-block\"\n" +
                        "                                    style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +
                        "                                    <span class=\"apple-link\"\n" +
                        "                                        style=\"color: #999999; font-size: 12px; text-align: center;\">Company Inc, 3\n" +
                        "                                        Abbey Road, San Francisco CA 94102</span>\n" +
                        "                                    <br> Don't like these emails? <a href=\"http://i.imgur.com/CScmqnj.gif\"\n" +
                        "                                        style=\"text-decoration: underline; color: #999999; font-size: 12px; text-align: center;\">Unsubscribe</a>.\n" +
                        "                                </td>\n" +
                        "                            </tr>\n" +
                        "                            <tr>\n" +
                        "                                <td class=\"content-block powered-by\"\n" +
                        "                                    style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +
                        "                                    Powered by <a href=\"http://htmlemail.io\"\n" +
                        "                                        style=\"color: #999999; font-size: 12px; text-align: center; text-decoration: none;\">HTMLemail</a>.\n" +
                        "                                </td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </div>\n" +
                        "                    <!-- END FOOTER -->\n" +
                        "\n" +
                        "                    <!-- END CENTERED WHITE CONTAINER -->\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "            <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
        String contentType = "text/html";
        rabbitMQSender.send(EmailObject.newEmailObj(recipientAddress, subject, confirmationUrl, message, contentType));
    }
    @Data
    @AllArgsConstructor
    public static class EmailObject {
        private String email;
        private String subject;
        private String confirmationUrl;
        private String message;
        private String contentType;

        public static EmailObject newEmailObj(String email, String subject, String confirmationUrl, String message, String contentType) {
            return new EmailObject(email, subject, confirmationUrl, message, contentType);
        }
    }
}