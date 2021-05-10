kubectl apply -f kong-gateway/plugin-configmaps.yaml
kubectl patch deployment ingress-kong -p "$(cat kong-gateway/plugin-depl.yaml)" -n kong