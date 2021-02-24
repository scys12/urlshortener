package config

import (
	"fmt"

	"github.com/spf13/viper"
)

func InitConfig() (config Config) {
	viper.SetConfigName("config")
	viper.SetConfigType("env")
	viper.AddConfigPath(".")
	err := viper.ReadInConfig()
	if err != nil {
		panic(fmt.Errorf("Fatal error config file: %s", err))
	}
	if err := viper.Unmarshal(&config); err != nil {
		panic(fmt.Errorf("failed to parse config file: %w", err))
	}
	return config
}
