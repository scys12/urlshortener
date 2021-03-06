package config

type Config struct {
	SMTPHost           string `mapstructure:"SMTP_HOST"`
	SMTPPort           int    `mapstructure:"SMTP_PORT"`
	SMTPUser           string `mapstructure:"MAIL_USERNAME"`
	SMTPPassword       string `mapstructure:"MAIL_PASSWORD"`
	SMTPEmail          string `mapstructure:"MAIL_FROM_ADDRESS"`
	RabbitMQHost       string `mapstructure:"RABBIT_HOST"`
	RabbitMQPort       int    `mapstructure:"RABBIT_PORT"`
	RabbitMQUser       string `mapstructure:"RABBIT_USERNAME"`
	RabbitMQPassword   string `mapstructure:"RABBIT_PASSWORD"`
	RabbitExchange     string `mapstructure:"RABBIT_EXCHANGE"`
	RabbitExchangeKind string `mapstructure:"RABBIT_EXCHANGE_KIND"`
	RabbitQueue        string `mapstructure:"RABBIT_QUEUE"`
	RabbitRoutingKey   string `mapstructure:"RABBIT_ROUTING_KEY"`
}
