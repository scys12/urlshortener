# URL Shortener

## Introduction

This is url shortener application built using microservice architecture.

Tech stack :

- BE: Go, Java(Spring Boot), Redis, MongoDB, Javascript(Express JS), RabbitMQ
- FE: React JS

## Development

1. Install [Kubernetes](https://kubernetes.io/), [Minikube](https://minikube.sigs.k8s.io/docs/start/), [Helm](https://helm.sh/), and [Skaffold](https://skaffold.dev/)

2. Init application with

```bash
cd backend
make init-app
```

3. Build k8s application

```bash
cd backend
make build-skaffold
```

4. Get Kong Gateway URL

```bash
minikube service -n kong kong-proxy --url
```

Copy the url and access the gateway from that url, e.g:

```bash
curl -i http:127.0.0.1:PORT
```
