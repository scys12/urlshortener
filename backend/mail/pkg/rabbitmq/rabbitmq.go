package rabbitmq

import (
	"encoding/json"
	"fmt"

	"github.com/scys12/urlshortener/mail/pkg/config"
	"github.com/scys12/urlshortener/mail/pkg/mailer"
	"github.com/sirupsen/logrus"
	"github.com/streadway/amqp"
)

type RabbitMQ struct {
	Host         string
	QueueName    string
	ExchangeName string
	ExchangeKind string
	Declare      bool
	Connection   *amqp.Connection
	Channel      *amqp.Channel
}

func NewRabbitMQ(config config.Config, declare bool) (rmq *RabbitMQ, err error) {
	rmq = &RabbitMQ{
		Host:         config.RabbitMQHost,
		QueueName:    config.RabbitQueue,
		ExchangeName: config.RabbitExchange,
		ExchangeKind: config.RabbitExchangeKind,
		Declare:      declare,
	}

	rmq.Connection, err = amqp.Dial(fmt.Sprintf("amqp://%s:%s@%s/", config.RabbitMQUser, config.RabbitMQPassword, config.RabbitMQHost))
	if err != nil {
		panic(err)
	}

	rmq.Channel, err = rmq.Connection.Channel()
	if err != nil {
		panic(err)
	}

	if declare {
		err = rmq.exchangeQueue()
		if err != nil {
			panic(err)
		}
		err = rmq.declareQueue()
		if err != nil {
			panic(err)
		}
		err = rmq.createQos()
		if err != nil {
			panic(err)
		}
	} else {
		err = rmq.queueBind()
		if err != nil {
			panic(err)
		}
	}

	return rmq, err
}

func (rmq *RabbitMQ) queueBind() (err error) {
	logrus.Debugf("Binding Queue: %s", rmq.QueueName)
	return rmq.Channel.QueueBind(
		rmq.QueueName,
		"",
		rmq.ExchangeName,
		false,
		nil,
	)
}

func (rmq *RabbitMQ) createQos() (err error) {
	logrus.Debug("Setting Qos Channel ...")
	err = rmq.Channel.Qos(
		1, 0, false,
	)
	return err
}

func (rmq *RabbitMQ) declareQueue() (err error) {
	logrus.Debugf("Declare Queue: %s", rmq.QueueName)
	_, err = rmq.Channel.QueueDeclare(
		rmq.ExchangeName,
		true,
		false,
		false,
		false,
		nil,
	)
	return err
}

func (rmq *RabbitMQ) exchangeQueue() (err error) {
	logrus.Debugf("Exchange Declare: %s", rmq.ExchangeName)
	err = rmq.Channel.ExchangeDeclare(
		rmq.ExchangeName,
		rmq.ExchangeKind,
		true,
		false,
		false,
		false,
		nil,
	)

	return err
}

func (rmq *RabbitMQ) ConsumeMessage() (err error) {
	defer rmq.Channel.Close()
	defer rmq.Connection.Close()

	msg, err := rmq.Channel.Consume(
		rmq.QueueName,
		"",
		false,
		false,
		false,
		false,
		nil,
	)

	if err != nil {
		return err
	}

	logrus.Info("Waiting messages...")
	for delivery := range msg {
		callback(delivery)
	}

	return nil
}

func callback(delivery amqp.Delivery) {
	email := new(mailer.Email)
	config := config.InitConfig()
	err := json.Unmarshal(delivery.Body, email)
	if err != nil {
		logrus.Error(err)
	}
	err = email.SendEmail(config)
	if err != nil {
		logrus.Error(err)
	} else {
		logrus.Debug("Email sent successfully")
	}

	delivery.Ack(false)
}
