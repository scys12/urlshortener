package main

import (
	"github.com/scys12/urlshortener/mail/pkg/config"
	"github.com/scys12/urlshortener/mail/pkg/rabbitmq"
)

func main() {
	config := config.InitConfig()
	rmq, err := rabbitmq.NewRabbitMQ(config, false)
	if err != nil {
		panic(err)
	}
	forever := make(chan bool)
	go rmq.ConsumeMessage()
	<-forever
}
