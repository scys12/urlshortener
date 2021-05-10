.PHONY : install-kong,install-plugin,install-rabbitmq,build-skaffold

install-kong:
	kubectl apply -f https://bit.ly/kong-ingress-dbless			

install-plugin:
	bash kong-gateway/build.sh

install-rabbitmq:
	helm install url-shortener-rabbitmq --set auth.username=guest,auth.password=guest,auth.erlangCookie=secretcookie bitnami/rabbitmq

build-skaffold:
	skaffold dev --no-prune=false --cache-artifacts=false

init-app:
	@make install-kong
	@make install-plugin
	@make install-rabbitmq