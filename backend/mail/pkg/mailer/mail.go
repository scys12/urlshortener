package mailer

import (
	"github.com/scys12/urlshortener/mail/pkg/config"
	"gopkg.in/gomail.v2"
)

type Email struct {
	To          string `json:"email"`
	Subject     string `json:"subject"`
	Body        string `json:"message"`
	ContentType string `json:"contentType"`
}

func (em *Email) SendEmail(config config.Config) error {
	email := gomail.NewMessage()
	email.SetHeader("From", config.SMTPEmail)
	email.SetHeader("To", em.To)
	email.SetHeader("Subject", em.Subject)
	email.SetBody(em.ContentType, em.Body)
	dial := gomail.NewDialer(config.SMTPHost, config.SMTPPort, config.SMTPUser, config.SMTPPassword)
	err := dial.DialAndSend(email)
	return err
}
